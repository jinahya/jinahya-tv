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


import java.util.ArrayList;
import java.util.List;
import javax.media.Player;
import javax.tv.service.selection.ServiceContentHandler;
import javax.tv.service.selection.ServiceContext;
import javax.tv.service.selection.ServiceContextFactory;
import javax.tv.service.selection.ServiceMediaHandler;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class ServiceContextFactoriesTest {


    @Test
    public void selectPlayersFromFactory() {

        final ServiceContentHandler handler
            = mock(ServiceContentHandler.class,
                   withSettings().extraInterfaces(Player.class));

        final ServiceContext context = mock(ServiceContext.class);
        when(context.getServiceContentHandlers())
            .thenReturn(new ServiceContentHandler[]{handler});

        final ServiceContextFactory factory = mock(ServiceContextFactory.class);
        when(factory.getServiceContexts())
            .thenReturn(new ServiceContext[]{context});

        final List<ServiceContentHandler> handlers = new ArrayList<>();

        ServiceContextFactories.contexts(
            factory, c -> true,
            c -> ServiceContexts.handlers(
                c, Player.class::isInstance, handlers::add)
        );

        assertTrue(handlers.size() == 1);
        assertTrue(handlers.get(0) instanceof Player);
        assertEquals(handlers.get(0), handler);
    }


    @Test
    public void selectServiceMediaHandlersFromFactory() {

        final ServiceContentHandler handler
            = mock(ServiceContentHandler.class,
                   withSettings().extraInterfaces(ServiceMediaHandler.class));

        final ServiceContext context = mock(ServiceContext.class);
        when(context.getServiceContentHandlers())
            .thenReturn(new ServiceContentHandler[]{handler});

        final ServiceContextFactory factory = mock(ServiceContextFactory.class);
        when(factory.getServiceContexts())
            .thenReturn(new ServiceContext[]{context});

        final List<ServiceContentHandler> handlers = new ArrayList<>();

        ServiceContextFactories.contexts(
            factory, c -> true,
            c -> ServiceContexts.handlers(
                c, ServiceMediaHandler.class::isInstance, handlers::add
            )
        );

        assertTrue(handlers.size() == 1);
        assertTrue(handlers.get(0) instanceof ServiceMediaHandler);
        assertEquals(handlers.get(0), handler);
    }


    @Test
    public void selectPlayersFromContext() {

        final ServiceContentHandler handler
            = mock(ServiceContentHandler.class,
                   withSettings().extraInterfaces(Player.class));

        final ServiceContext context = mock(ServiceContext.class);
        when(context.getServiceContentHandlers())
            .thenReturn(new ServiceContentHandler[]{handler});

        final List<ServiceContentHandler> handlers = new ArrayList<>();

        ServiceContexts.handlers(context, Player.class::isInstance,
                                 handlers::add);

        assertTrue(handlers.size() == 1);
        assertTrue(handlers.get(0) instanceof Player);
        assertEquals(handlers.get(0), handler);
    }


    @Test
    public void selectServiceMediaHandlersFromContext() {

        final ServiceContentHandler handler
            = mock(ServiceContentHandler.class,
                   withSettings().extraInterfaces(ServiceMediaHandler.class));

        final ServiceContext context = mock(ServiceContext.class);
        when(context.getServiceContentHandlers())
            .thenReturn(new ServiceContentHandler[]{handler});

        final List<ServiceContentHandler> handlers = new ArrayList<>();
        ServiceContexts.handlers(
            context, ServiceMediaHandler.class::isInstance, handlers::add);

        assertTrue(handlers.size() == 1);
        assertTrue(handlers.get(0) instanceof ServiceMediaHandler);
        assertEquals(handlers.get(0), handler);
    }


}

