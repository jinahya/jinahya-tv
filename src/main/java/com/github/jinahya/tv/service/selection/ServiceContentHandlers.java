/*
 * Copyright 2014 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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


package com.github.jinahya.tv.service.selection;


import java.util.Collection;
import javax.tv.service.selection.ServiceContentHandler;
import javax.tv.service.selection.ServiceContext;
import javax.tv.service.selection.ServiceContextException;
import javax.tv.service.selection.ServiceContextFactory;
import javax.tv.xlet.XletContext;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class ServiceContentHandlers {


//    /**
//     * Tests and consumes the result of
//     * {@link ServiceContext#getServiceContentHandlers()}.
//     *
//     * @param <R>
//     * @param context the context whose handlers are processed.
//     * @param predicate the predicate to test ServiceContentHandler instance
//     * before accept to {@code consumer}. {@code null} allowed.
//     * @param function
//     * @param consumer the consumer which accepts {@link ServiceContentHandler}.
//     * @param limit
//     *
//     * @see ServiceContext#getServiceContentHandlers()
//     */
//    public static <R> void list(
//        final ServiceContext context,
//        final Predicate<ServiceContentHandler> predicate,
//        final Function<ServiceContentHandler, R> function,
//        final Consumer<R> consumer, final int limit) {
//
//        if (context == null) {
//            throw new NullPointerException("null context");
//        }
//
//        int count = 0;
//        for (final ServiceContentHandler handler
//             : context.getServiceContentHandlers()) {
//            if (limit >= 0 && count == limit) {
//                break;
//            }
//            if (predicate.test(handler)) {
//                consumer.accept(function.apply(handler));
//                count++;
//            }
//        }
//    }
    public static <T extends ServiceContentHandler> Collection<? super T> collect(
        final ServiceContext context, final Class<T> type,
        final Collection<? super T> collection, final int limit) {

        if (context == null) {
            throw new NullPointerException("null context");
        }

        if (type == null) {
            throw new NullPointerException("null type");
        }

        if (collection == null) {
            throw new NullPointerException("null collection");
        }

        int count = 0;
        for (final ServiceContentHandler handler
             : context.getServiceContentHandlers()) {
            if (limit >= 0 && count == limit) {
                break;
            }
            if (type.isInstance(handler)) {
                collection.add(type.cast(handler));
                count++;
            }
        }

        return collection;
    }


    public static <T extends ServiceContentHandler> Collection<? super T> collect(
        final XletContext context, final Class<T> type,
        final Collection<? super T> collection, final int limit)
        throws ServiceContextException {

        if (context == null) {
            throw new NullPointerException("null context");
        }

        return collect(
            ServiceContextFactory.getInstance().getServiceContext(context),
            type, collection, limit);
    }


    private ServiceContentHandlers() {

        super();
    }


}

