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

import java.util.concurrent.Callable;

/**
 * @author lianglipeng.llp@alibaba-inc.com
 * @version $Id: BizCallable.java, v 0.1 2024年05月10日 11:31 立蓬 Exp $
 */
public class KouplelessCallable<T> implements Callable<T> {
    private Callable<T> callable;

    private ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    public KouplelessCallable(Callable<T> callable) {
        this.callable = callable;
    }

    public static <T> Callable<T> wrap(Callable<T> callable) {
        if (callable == null)
            throw new NullPointerException();

        if (callable instanceof KouplelessCallable) {
            return callable;
        }
        return new KouplelessCallable<T>(callable);
    }

    @Override
    public T call() throws Exception {
        ClassLoader currentClassloader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(classLoader);
        try {
            return this.callable.call();
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassloader);
        }
    }
}