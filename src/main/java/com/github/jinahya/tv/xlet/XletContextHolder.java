/*
 * Copyright 2014 <a href="mailto:onacit@gmail.com">Jin Kwon</a>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.github.jinahya.tv.xlet;


import javax.tv.xlet.XletContext;


/**
 *
 * <blockquote><pre>
 * {@code
 * public void initXlet(final XletContext xletContext) {
 *     XletContextHolder.getInstance().set(xletContext);
 *     // other statements here
 * }
 * public void destroyXlet(final boolean unconditional) {
 *     // other statements here
 *     XletContextHolder.getInstance().set(null);
 * }
 * }
 * </pre></blockquote>
 * <p>
 * <blockquote></pre> null {@code
 * class SomeOther {
 *     public void doSomething() {
 *         final XletContext xletContext
 *             = XletContextHolder.getInstance().get();
 *     }
 * }
 * }
 * </pre></blockquote></p>
 *
 * @author Jin Kwon &ltjinahya at gmail.com&gt;
 */
public final class XletContextHolder {


    private static volatile XletContextHolder INSTANCE;


    /**
     *
     * @return the instance.
     */
    public static XletContextHolder getInstance() {

        if (INSTANCE == null) {
            synchronized (XletContextHolder.class) {
                if (INSTANCE == null) {
                    INSTANCE = new XletContextHolder();
                }
            }
        }

        return INSTANCE;
    }


    private XletContextHolder() {

        super();
    }


    /**
     *
     * @param xletContext the xlet context to be set
     */
    public void set(final XletContext xletContext) {

        if (xletContext == null) {
            synchronized (this) {
                if (this.xletContext != null) {
                    this.xletContext = null;
                    return;
                }
            }
            throw new NullPointerException("null xletContext");
        }

        synchronized (this) {
            if (this.xletContext != null) {
                throw new IllegalStateException("already set");
            }
            this.xletContext = xletContext;
        }
    }


    public XletContext get() {

        synchronized (this) {
            if (xletContext == null) {
                throw new IllegalStateException("not set yet");
            }
            return xletContext;
        }
    }


    private volatile XletContext xletContext;


}

