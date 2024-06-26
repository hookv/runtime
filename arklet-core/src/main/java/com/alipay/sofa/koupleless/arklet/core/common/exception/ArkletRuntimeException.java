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
package com.alipay.sofa.koupleless.arklet.core.common.exception;

/**
 * <p>ArkletRuntimeException class.</p>
 *
 * @author mingmen
 * @since 2023/6/14
 * @version 1.0.0
 */
public class ArkletRuntimeException extends ArkletException {
    /**
     * <p>Constructor for ArkletRuntimeException.</p>
     */
    public ArkletRuntimeException() {
    }

    /**
     * <p>Constructor for ArkletRuntimeException.</p>
     *
     * @param message a {@link java.lang.String} object
     */
    public ArkletRuntimeException(String message) {
        super(message);
    }

    /**
     * <p>Constructor for ArkletRuntimeException.</p>
     *
     * @param message a {@link java.lang.String} object
     * @param cause a {@link java.lang.Throwable} object
     */
    public ArkletRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <p>Constructor for ArkletRuntimeException.</p>
     *
     * @param cause a {@link java.lang.Throwable} object
     */
    public ArkletRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * <p>Constructor for ArkletRuntimeException.</p>
     *
     * @param message a {@link java.lang.String} object
     * @param cause a {@link java.lang.Throwable} object
     * @param enableSuppression a boolean
     * @param writableStackTrace a boolean
     */
    public ArkletRuntimeException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * <p>Constructor for ArkletRuntimeException.</p>
     *
     * @param format a {@link java.lang.String} object
     * @param args a {@link java.lang.Object} object
     */
    public ArkletRuntimeException(String format, Object... args) {
        super(format, args);
    }

    /**
     * <p>Constructor for ArkletRuntimeException.</p>
     *
     * @param cause a {@link java.lang.Throwable} object
     * @param format a {@link java.lang.String} object
     * @param args a {@link java.lang.Object} object
     */
    public ArkletRuntimeException(Throwable cause, String format, Object... args) {
        super(cause, format, args);
    }
}
