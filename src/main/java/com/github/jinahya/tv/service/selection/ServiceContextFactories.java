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
import javax.tv.service.selection.ServiceContext;
import javax.tv.service.selection.ServiceContextFactory;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class ServiceContextFactories {


    public static interface ContextPredicate {


        boolean test(ServiceContext context);


    }


    public static final class ContextPredicates {


        public static final ContextPredicate TRUE = new ContextPredicate() {


            public boolean test(final ServiceContext context) {

                return true;
            }


        };


        public static final ContextPredicate FALSE = new ContextPredicate() {


            public boolean test(final ServiceContext context) {

                return false;
            }


        };


        private ContextPredicates() {

            super();
        }


    }


    public static interface ContextConsumer {


        void accept(ServiceContext context);


    }


    public static final class ContextConsumers {


        public static final ContextConsumer EMPTY = new ContextConsumer() {


            public void accept(final ServiceContext context) {

            }


        };


        public static ContextConsumer collecting(final Collection collection) {

            return new ContextConsumer() {


                public void accept(ServiceContext context) {
                    collection.add(context);
                }


            };
        }


        private ContextConsumers() {

            super();
        }


    }


    public static void contexts(final ServiceContextFactory factory,
                                final ContextPredicate predicate,
                                final ContextConsumer consumer) {

        final ServiceContext[] contexts = factory.getServiceContexts();
        for (int i = 0; i < contexts.length; i++) {
            if (predicate.test(contexts[i])) {
                consumer.accept(contexts[i]);
            }
        }
    }


    public static void contexts(final ServiceContextFactory factory,
                                final ContextConsumer consumer) {

        contexts(factory, ContextPredicates.TRUE, consumer);
    }


    private ServiceContextFactories() {

        super();
    }


}

