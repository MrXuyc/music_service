package com.sunshine.music.common.task;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.*;

import com.sunshine.music.util.JsonUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class TaskServerImpl implements TaskServer {

    @Resource(name = "apiTaskThreadPollExecutor")
    private ThreadPoolExecutor apiTaskThreadPoolExecutor;

    @Resource(name = "mailTaskThreadPoolExecutor")
    private ThreadPoolExecutor mailTaskThreadPoolExecutor;

    @Resource(name = "preApiTaskThreadPollExecutor")
    private ThreadPoolExecutor preApiTaskThreadPollExecutor;

    private ListeningExecutorService apiListeningExecutorService;
    private ListeningExecutorService mailListeningExecutorService;
    private ListeningExecutorService preApiListeningExecutorService;

    @PostConstruct
    private void init() {
        apiListeningExecutorService = MoreExecutors.listeningDecorator(apiTaskThreadPoolExecutor);
        mailListeningExecutorService = MoreExecutors.listeningDecorator(mailTaskThreadPoolExecutor);
        preApiListeningExecutorService = MoreExecutors.listeningDecorator(preApiTaskThreadPollExecutor);
    }

    @Override
    public <T> Map<String, Optional<T>> runTasks(Map<String, Task<T>> tasks, long maxTimeout, POOL pool) {
        Preconditions.checkNotNull(pool, "pool enum is null");
        ListeningExecutorService currListeningExecutorService = apiListeningExecutorService;
        switch (pool) {
            case API:currListeningExecutorService = apiListeningExecutorService;break;
            case MAIL:currListeningExecutorService = mailListeningExecutorService;break;
            case PRE_API:currListeningExecutorService = preApiListeningExecutorService;break;
        }
        CountDownLatch countDownLatch = new CountDownLatch(tasks.size());
        Map<String, Optional<T>> resMap = Maps.newConcurrentMap();

        List<Future> futureList = Lists.newArrayList();
        for (final Map.Entry<String, Task<T>> taskEntry : tasks.entrySet()) {
            ListenableFuture<T> future = currListeningExecutorService.submit(taskEntry.getValue());
            futureList.add(future);
            Futures.addCallback(future, new FutureCallback<T>() {
                @Override
                public void onSuccess(T result) {
                    try {
                        resMap.put(taskEntry.getKey(), Optional.fromNullable(result));
                    } finally {
                        countDownLatch.countDown();
                    }

                }

                @Override
                public void onFailure(Throwable t) {
                    try {
                        resMap.put(taskEntry.getKey(), Optional.absent());
                    } finally {
                        log.error("task.task_fail",t);
                        countDownLatch.countDown();
                    }
                }
            });
        }

        try {
            boolean allDownFlag = countDownLatch.await(maxTimeout, TimeUnit.MILLISECONDS);
            if (!allDownFlag) {
                for(Future future : futureList) {
                    future.cancel(true); // 关闭超时异常任务
                }
            }
            log.info("[tasks_done][allsuccessflag={}][size={}]", allDownFlag, tasks.size());
        } catch (InterruptedException e) {
            log.error("countDownLatch.await InterruptedException", e);
            Thread.currentThread().interrupt();
        }
        // 将异常的填上absent
        tasks.keySet().stream().filter(key -> !resMap.containsKey(key)).forEach(key -> {
            resMap.put(key, Optional.absent());
        });
        if (log.isDebugEnabled()) {
            log.debug("{}", JsonUtils.toJson(resMap));
        }
        return resMap;
    }

    @Override
    public <T> Map<String, Optional<T>> runTasks(Map<String, Task<T>> tasks, POOL pool) {
        return runTasks(tasks,
                DEFAULT_TASK_TIMEOUT, pool);
    }
}
