/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.koupleless.plugin.concurrent;

import com.alipay.sofa.koupleless.plugin.manager.handler.ShutdownExecutorServicesOnUninstallEventHandler;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * @author lianglipeng.llp@alibaba-inc.com
 * @version $Id: KouplelessScheduledExecutorServiceAdaptor.java, v 0.1 2024年05月13日 15:54 立蓬 Exp $
 */
public class KouplelessScheduledExecutorService implements ScheduledExecutorService {
    private final ScheduledExecutorService scheduledExecutorService;

    public KouplelessScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
        ShutdownExecutorServicesOnUninstallEventHandler
            .manageExecutorService(this.scheduledExecutorService);
    }

    @Override
    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        return scheduledExecutorService.schedule(KouplelessRunnable.wrap(command), delay, unit);
    }

    @Override
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
        return scheduledExecutorService.schedule(KouplelessCallable.wrap(callable), delay, unit);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period,
                                                  TimeUnit unit) {
        return scheduledExecutorService.scheduleAtFixedRate(KouplelessRunnable.wrap(command),
            initialDelay, period, unit);
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay,
                                                     long delay, TimeUnit unit) {
        return scheduledExecutorService.scheduleWithFixedDelay(KouplelessRunnable.wrap(command),
            initialDelay, delay, unit);
    }

    @Override
    public void shutdown() {
        scheduledExecutorService.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return scheduledExecutorService.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return scheduledExecutorService.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return scheduledExecutorService.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return scheduledExecutorService.awaitTermination(timeout, unit);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return scheduledExecutorService.submit(KouplelessCallable.wrap(task));
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return scheduledExecutorService.submit(KouplelessRunnable.wrap(task), result);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return scheduledExecutorService.submit(KouplelessRunnable.wrap(task));
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return scheduledExecutorService
            .invokeAll(tasks.stream().map(KouplelessCallable::wrap).collect(Collectors.toList()));
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout,
                                         TimeUnit unit) throws InterruptedException {
        return scheduledExecutorService.invokeAll(
            tasks.stream().map(KouplelessCallable::wrap).collect(Collectors.toList()), timeout,
            unit);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException,
                                                                    ExecutionException {
        return scheduledExecutorService
            .invokeAny(tasks.stream().map(KouplelessCallable::wrap).collect(Collectors.toList()));
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout,
                           TimeUnit unit) throws InterruptedException, ExecutionException,
                                          TimeoutException {
        return scheduledExecutorService.invokeAny(
            tasks.stream().map(KouplelessCallable::wrap).collect(Collectors.toList()), timeout,
            unit);
    }

    @Override
    public void execute(Runnable command) {
        scheduledExecutorService.execute(KouplelessRunnable.wrap(command));
    }
}