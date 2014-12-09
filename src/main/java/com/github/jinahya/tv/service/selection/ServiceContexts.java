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


    /**
     * An interface for testing instances of {@link ServiceContentHandler}.
     */
    public static interface HandlerPredicate {


        /**
         * Tests specified {@code handler}.
         *
         * @param handler the handler to test
         *
         * @return {@code true} if acceptable, {@code false} otherwise.
         */
        boolean test(ServiceContentHandler handler);


    }


    /**
     * A utility class for {@link HandlerPredicate}.
     */
    public static final class HandlerPredicates {


        /**
         * A predefined constant accepts all.
         */
        public static final HandlerPredicate TRUE = new HandlerPredicate() {


            public boolean test(final ServiceContentHandler handler) {

                return true;
            }


        };


        /**
         * A predefined constant accepts none.
         */
        public static final HandlerPredicate FALSE = new HandlerPredicate() {


            public boolean test(final ServiceContentHandler handler) {

                return false;
            }


        };


        private static class InstanceOfAny implements HandlerPredicate {


            public InstanceOfAny(final Class[] types) {

                super();

                this.types = types;
            }


            public boolean test(final ServiceContentHandler handler) {

                for (int i = 0; i < types.length; i++) {
                    if (types[i].isInstance(handler)) {
                        return true;
                    }
                }

                return false;
            }


            private final Class[] types;


        }


        /**
         * Creates a new predicate which tests whether a
         * {@link ServiceContentHandler} is instance of any given {@code types}.
         *
         * @param types the type to check
         *
         * @return true if given {@link ServiceContentHandler} is instance of
         * any given {@code types}; {@code false} otherwise.
         */
        public static HandlerPredicate instanceOfAny(final Class[] types) {

            return new InstanceOfAny(types);
        }


        public static HandlerPredicate instanceOfAny(final Class firstType) {

            return instanceOfAny(new Class[]{firstType});
        }


        public static HandlerPredicate instanceOfAny(final Class firstType,
                                                     final Class secondType) {

            return instanceOfAny(new Class[]{firstType, secondType});
        }


        public static HandlerPredicate instanceOfAny(final Class firstType,
                                                     final Class secondType,
                                                     final Class thirdType) {

            return instanceOfAny(new Class[]{firstType, secondType, thirdType});
        }


        public static class InstanceOfAll implements HandlerPredicate {


            public InstanceOfAll(final Class[] types) {

                super();

                this.types = types;
            }


            public boolean test(final ServiceContentHandler handler) {

                for (int i = 0; i < types.length; i++) {
                    if (!types[i].isInstance(handler)) {
                        return false;
                    }
                }

                return true;
            }


            private final Class[] types;


        }


        public static HandlerPredicate instanceOfAll(final Class[] types) {

            return new InstanceOfAll(types);
        }


        public static HandlerPredicate instanceOfAll(final Class firstType) {

            return instanceOfAll(new Class[]{firstType});
        }


        public static HandlerPredicate instanceOfAll(final Class firstType,
                                                     final Class secondType) {

            return instanceOfAll(new Class[]{firstType, secondType});
        }


        public static HandlerPredicate instanceOfAll(final Class firstType,
                                                     final Class secondType,
                                                     final Class thirdType) {

            return instanceOfAll(new Class[]{firstType, secondType, thirdType});
        }


        private HandlerPredicates() {

            super();
        }


    }


    /**
     * An interface for consuming instances of {@link ServiceContentHandler}.
     */
    public static interface HandlerConsumer {


        /**
         * Consumes given handler.
         *
         * @param handler the handler to consume
         */
        void accept(ServiceContentHandler handler);


    }


    /**
     * A utility class for consumers.
     */
    public static final class HandlerConsumers {


        /**
         * A consumer doesn't consumes.
         */
        public static final HandlerConsumer EMPTY = new HandlerConsumer() {


            public void accept(final ServiceContentHandler handler) {
            }


        };


        private static class HandlerCollector implements HandlerConsumer {


            public HandlerCollector(final Collection collection) {

                super();

                this.collection = collection;
            }


            public void accept(final ServiceContentHandler handler) {

                collection.add(handler);
            }


            private final Collection collection;


        }


        /**
         * A consumer collecting handlers to a specified collection.
         *
         * @param collection the collection to which handlers added
         *
         * @return a consumer
         */
        public static HandlerConsumer collecting(final Collection collection) {

            return new HandlerCollector(collection);
        }


        private HandlerConsumers() {

            super();
        }


    }


    /**
     * Tests and consumes {@link ServiceContentHandler} instances of a
     * {@link ServiceContext}.
     *
     * @param context the context whose
     * {@link ServiceContext#getServiceContentHandlers()} are tested and
     * consumed.
     * @param predicate the tester
     * @param consumer the consumer
     *
     * @see ServiceContext#getServiceContentHandlers()
     */
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


    /**
     *
     * @param context
     * @param collection
     *
     * @return given {@code collection}.
     */
    public static Collection handlers(final ServiceContext context,
                                      final Collection collection) {

        handlers(context, HandlerConsumers.collecting(collection));

        return collection;
    }


    private ServiceContexts() {

        super();
    }


}

