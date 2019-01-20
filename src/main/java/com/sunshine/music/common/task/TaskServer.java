package com.sunshine.music.common.task;

import com.google.common.base.Optional;

import java.util.Map;

public interface TaskServer {

    int DEFAULT_TASK_TIMEOUT = 2000;
    public enum POOL {
        PRE_API, API, MAIL
    }

    <T> Map<String, Optional<T>> runTasks(Map<String, Task<T>> tasks, long maxTimeout, POOL pool);

    <T> Map<String, Optional<T>> runTasks(Map<String, Task<T>> tasks, POOL pool);
}
