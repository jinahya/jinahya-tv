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
import javax.tv.service.selection.ServiceContext;
import javax.tv.service.selection.ServiceContextFactory;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class ServiceContextFactories {


    public static interface ContextPredicate {


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


        boolean test(ServiceContext context);


    }


    public static interface ContextConsumer {


        public static final ContextConsumer EMPTY = new ContextConsumer() {


            public void accept(final ServiceContext context) {
            }


        };


        void accept(ServiceContext context);


    }


    public static class ContextCollector implements ContextConsumer {


        public ContextCollector(final Collection collection) {

            super();

            this.collection = collection;
        }


        public void accept(final ServiceContext context) {

            collection.add(context);
        }


        private final Collection collection;


    }


    public static void contexts(final ServiceContextFactory factory,
                                final ContextPredicate predicate,
                                final ContextConsumer operator) {

        final ServiceContext[] contexts = factory.getServiceContexts();
        for (int i = 0; i < contexts.length; i++) {
            if (predicate.test(contexts[i])) {
                operator.accept(contexts[i]);
            }
        }
    }


    public static List contexts(final ServiceContextFactory factory,
                                final ContextPredicate predicate,
                                final List list) {

        contexts(factory, predicate, new ContextCollector(list));

        return list;
    }


    private ServiceContextFactories() {

        super();
    }


}

