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


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class ServiceContexts {


    public static interface HandlerPredicate {


        boolean test(ServiceContentHandler handler);


    }


    public static final class HandlerPredicates {


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


        public static HandlerPredicate anyOf(final Class[] types) {
            return new HandlerPredicate() {


                public boolean test(final ServiceContentHandler handler) {

                    for (int i = 0; i < types.length; i++) {
                        if (types[i].isInstance(handler)) {
                            return true;
                        }
                    }

                    return false;
                }


            };
        }


        public static HandlerPredicate anyOf(final Class firstType) {

            return anyOf(new Class[]{firstType});
        }


        public static HandlerPredicate anyOf(final Class firstType,
                                             final Class secondType) {

            return anyOf(new Class[]{firstType, secondType});
        }


        public static HandlerPredicate anyOf(final Class firstType,
                                             final Class secondType,
                                             final Class thirdType) {

            return anyOf(new Class[]{firstType, secondType, thirdType});
        }


        public static HandlerPredicate allOf(final Class[] types) {

            return new HandlerPredicate() {


                public boolean test(final ServiceContentHandler handler) {

                    for (int i = 0; i < types.length; i++) {
                        if (!types[i].isInstance(handler)) {
                            return false;
                        }
                    }

                    return true;
                }


            };
        }


        public static HandlerPredicate allOf(final Class firstType) {

            return allOf(new Class[]{firstType});
        }


        public static HandlerPredicate allOf(final Class firstType,
                                             final Class secondType) {

            return allOf(new Class[]{firstType, secondType});
        }


        public static HandlerPredicate allOf(final Class firstType,
                                             final Class secondType,
                                             final Class thirdType) {

            return allOf(new Class[]{firstType, secondType, thirdType});
        }


        private HandlerPredicates() {

            super();
        }


    }


    public static interface HandlerConsumer {


        void accept(ServiceContentHandler handler);


    }


    public static final class HandlerConsumers {


        public static final HandlerConsumer EMPTY = new HandlerConsumer() {


            public void accept(final ServiceContentHandler handler) {
            }


        };


        public static HandlerConsumer collecting(final Collection collection) {

            return new HandlerConsumer() {


                public void accept(final ServiceContentHandler handler) {

                    collection.add(handler);
                }


            };
        }


        private HandlerConsumers() {

            super();
        }


    }


    public static void handlers(final ServiceContext context,
                                final HandlerPredicate predicate,
                                final HandlerConsumer consumer) {

        if (context == null) {
            throw new NullPointerException("null context");
        }

        final ServiceContentHandler[] handlers
            = context.getServiceContentHandlers();
        for (int i = 0; i < handlers.length; i++) {
            if (predicate.test(handlers[i])) {
                consumer.accept(handlers[i]);
            }
        }
    }


    public static void handlers(final ServiceContext context,
                                final HandlerConsumer consumer) {

        handlers(context, HandlerPredicates.TRUE, consumer);
    }


    private ServiceContexts() {

        super();
    }


}

