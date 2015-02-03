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


import com.github.jinahya.util.SynchronizedHolder;
import javax.tv.xlet.XletContext;


/**
 * A utility class holding an {@link XletContext}.
 * <p>
 * <blockquote><pre>
 * {@code
 * public void initXlet(final XletContext xletContext) {
 *     XletContextHolder.getIntstance().set(xletContext);
 *     // other statements here
 * }
 *
 * public void destroyXlet(final boolean unconditional) {
 *     // other statements here
 *     XletContextHolder.getInstance().set(null);
 * }
 * }
 * </pre></blockquote>
 * </p>
 * Not any codes executed between {@code initXlet} and {@code destroyXlet} can
 * use it like this.
 * <p>
 * <blockquote><pre>
 * {@code
 * class SomeOther {
 *     public void doSomethingWithXletContext() {
 *         final XletContext xletContext
 *             = XletContextHolder.getInstance().get();
 *         // do something with xletContext
 *     }
 * }
 * }
 * </pre></blockquote>
 * </p>
 *
 * @author Jin Kwon &ltjinahya at gmail.com&gt;
 */
public final class XletContextHolder extends SynchronizedHolder<XletContext> {


    private static final class InstanceHolder {


        private static final XletContextHolder INSTANCE
            = new XletContextHolder();


        private InstanceHolder() {

            super();
        }


    }


    public static XletContextHolder getInstance() {

        return InstanceHolder.INSTANCE;
    }


    /**
     * Return the value which {@link #getInstance()} is holding. This method is
     * identical to {@code getInstance().get()}.
     *
     * @return the value.
     *
     * @see #getInstance()
     * @see #get()
     */
    public static XletContext getXletContext() {

        return getInstance().get();
    }


    /**
     * Replace the value which {@link #getInstance()} is holding. This method is
     * identical to {@code getInstance().set(holdee)}.
     *
     * @param holdee
     *
     * @see #getInstance()
     * @see #set(javax.tv.xlet.XletContext)
     */
    public static void setXletContext(final XletContext holdee) {

        getInstance().set(holdee);
    }


    private XletContextHolder() {

        super();
    }


    @Override
    public synchronized XletContext get() {

        final XletContext holdee = super.get();
        if (holdee == null) {
            throw new IllegalStateException("no holdee");
        }

        return holdee;
    }


    /**
     * Replaces the holding value with specified.
     *
     * @param holdee new value; {@code null} for clear.
     */
    @Override
    public synchronized void set(final XletContext holdee) {

        if (holdee == null) {
            if (super.get() != null) {
                set(holdee);
                return;
            }
            throw new NullPointerException("null holdee");
        }

        if (super.get() != null) {
            throw new IllegalStateException("already set");
        }

        super.set(holdee);
    }


}

