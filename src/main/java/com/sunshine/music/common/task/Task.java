package com.sunshine.music.common.task;

import java.util.concurrent.Callable;

public abstract class Task<T> implements Callable<T> {
    protected abstract String getTaskId();
}
