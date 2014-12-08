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
import java.util.List;
import javax.tv.service.selection.ServiceContentHandler;
import javax.tv.service.selection.ServiceContext;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class ServiceContexts {


    public static interface HandlerPredicate {


        public static final HandlerPredicate TRUE = new HandlerPredicate() {


            public boolean test(final ServiceContentHandler handler) {

                return true;
            }


        };


        public static final HandlerPredicate FALSE = new HandlerPredicate() {


            public boolean test(final ServiceContentHandler handler) {

                return false;
            }


        };


        boolean test(ServiceContentHandler handler);


    }


    public static interface HandlerConsumer {


        public static final HandlerConsumer EMPTY = new HandlerConsumer() {


            public void accept(final ServiceContentHandler handler) {
            }


        };


        void accept(ServiceContentHandler handler);


    }


    public static class HandlerCollector implements HandlerConsumer {


        public HandlerCollector(final Collection collection) {

            super();

            this.collection = collection;
        }


        public void accept(final ServiceContentHandler handler) {

            collection.add(handler);
        }


        private final Collection collection;


    }


    public static void handlers(final ServiceContext context,
                                final HandlerPredicate predicate,
                                final HandlerConsumer operator) {

        if (context == null) {
            throw new NullPointerException("null context");
        }

        final ServiceContentHandler[] handlers
            = context.getServiceContentHandlers();
        for (int i = 0; i < handlers.length; i++) {
            if (predicate.test(handlers[i])) {
                operator.accept(handlers[i]);
            }
        }
    }


    public static List handlers(final ServiceContext context,
                                final HandlerPredicate predicate,
                                final List list) {

        handlers(context, predicate, new HandlerCollector(list));

        return list;
    }


//    public static List handlers(final ServiceContextFactory factory,
//                                final HandlerPredicate predicate,
//                                final List list) {
//
//        if (factory == null) {
//            throw new NullPointerException("null factory");
//        }
//
//        if (list == null) {
//            throw new NullPointerException("null list");
//        }
//
//        final ServiceContext[] contexts = factory.getServiceContexts();
//        for (int i = 0; i < contexts.length; i++) {
//            handlers(contexts[i], predicate, list);
//        }
//
//        return list;
//    }
    private ServiceContexts() {

        super();
    }


}

