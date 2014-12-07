/*
 * Copyright 2014 Jin Kwon &ltjinahya at gmail.com&gt;.
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


import java.util.ArrayList;
import java.util.List;
import javax.tv.service.selection.ServiceContentHandler;
import javax.tv.service.selection.ServiceContext;
import javax.tv.service.selection.ServiceContextException;
import javax.tv.service.selection.ServiceContextFactory;
import javax.tv.service.selection.ServiceMediaHandler;
import javax.tv.xlet.XletContext;


/**
 *
 * @author Jin Kwon &lt;jinahya at gmail.com&gt;
 */
public final class ServiceMediaHandlers {


    /**
     * Returns a list of {@link ServiceContentHandler}s related to given
     * {@code xletContext} which each is an instance of
     * {@link ServiceMediaHandler}.
     *
     * @param xletContext the xlet context
     * @return a list of {@link ServiceContentHandler}s which each is an
     * instance of {@link ServiceMediaHandler}.
     * @throws ServiceContextException
     * @see ServiceContextFactory#getInstance()
     */
    public static List get(final XletContext xletContext)
        throws ServiceContextException {

        if (xletContext == null) {
            throw new NullPointerException("null xletContext");
        }

        final List list = new ArrayList();

        final ServiceContextFactory serviceContxtFactory
            = ServiceContextFactory.getInstance();
        final ServiceContext serviceContext
            = serviceContxtFactory.getServiceContext(xletContext);
        final ServiceContentHandler[] serviceContentHandlers
            = serviceContext.getServiceContentHandlers();
        for (int i = 0; i < serviceContentHandlers.length; i++) {
            if (serviceContentHandlers[i] instanceof ServiceMediaHandler) {
                list.add(serviceContentHandlers[i]);
            }
        }

        return list;
    }


    public static List get(final ServiceContext context, final List list) {

        if (context == null) {
            throw new NullPointerException("null context");
        }

        if (list == null) {
            throw new NullPointerException("null list");
        }

        final ServiceContentHandler[] handlers
            = context.getServiceContentHandlers();
        for (int i = 0; i < handlers.length; i++) {
            if (handlers[i] instanceof ServiceMediaHandler) {
                list.add(handlers[i]);
            }
        }

        return list;
    }


    public static List get(final ServiceContext context) {

        return get(context, new ArrayList());
    }


    public static List get(final ServiceContextFactory factory,
                           final List serviceMediaHandlers) {

        if (factory == null) {
            throw new NullPointerException("null factory");
        }

        if (serviceMediaHandlers == null) {
            throw new NullPointerException("null serviceMediaHandlers");
        }

        final ServiceContext[] contexts = factory.getServiceContexts();
        for (int i = 0; i < contexts.length; i++) {
            get(contexts[i], serviceMediaHandlers);
        }

        return serviceMediaHandlers;
    }


    public static List get(final ServiceContextFactory factory) {

        if (factory == null) {
            throw new NullPointerException("null factory");
        }

        return get(factory, new ArrayList());
    }


    public static List get() {

        return get(ServiceContextFactory.getInstance());
    }


    private ServiceMediaHandlers() {

        super();
    }


}

