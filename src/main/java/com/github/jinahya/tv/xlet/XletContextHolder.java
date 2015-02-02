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
 * <p>
 * <blockquote><pre>
 * {@code
 * public void initXlet(final XletContext xletContext) {
 *     XletContextHolder.set(xletContext);
 *     // other statements here
 * }
 * public void destroyXlet(final boolean unconditional) {
 *     // other statements here
 *     XletContextHolder.set(null);
 * }
 * }
 * </pre></blockquote>
 * </p>
 * <p>
 * <blockquote><pre>
 * {@code
 * class SomeOther {
 *     public void doSomething() {
 *         final XletContext xletContext = XletContextHolder.get();
 *     }
 * }
 * }
 * </pre></blockquote>
 * </p>
 *
 * @author Jin Kwon &ltjinahya at gmail.com&gt;
 */
public final class XletContextHolder {


    private static volatile XletContext HOLDEE;


    public synchronized static XletContext get() {

        if (HOLDEE == null) {
            throw new IllegalStateException("not set yet");
        }

        return HOLDEE;
    }


    /**
     *
     * @param xletContext the xlet context to be set
     */
    public static void set(final XletContext xletContext) {

        if (xletContext == null) {
            synchronized (XletContextHolder.class) {
                if (HOLDEE != null) {
                    HOLDEE = null;
                    return;
                }
            }
            throw new NullPointerException("null xletContext");
        }

        synchronized (XletContextHolder.class) {
            if (HOLDEE != null) {
                throw new IllegalStateException("already set");
            }
            HOLDEE = xletContext;
        }
    }


    private XletContextHolder() {

        super();
    }


}

