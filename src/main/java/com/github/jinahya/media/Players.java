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


package com.github.jinahya.media;


import java.util.ArrayList;
import java.util.List;
import javax.media.Player;
import javax.tv.service.selection.ServiceContentHandler;
import javax.tv.service.selection.ServiceContext;
import javax.tv.service.selection.ServiceContextException;
import javax.tv.service.selection.ServiceContextFactory;
import javax.tv.xlet.XletContext;


/**
 *
 * @author Jin Kwon &ltjinahya at gmail.com&gt;
 */
public final class Players {


    public static List players(final XletContext xletContext)
        throws ServiceContextException {

        final List playerList = new ArrayList();

        final ServiceContentHandler[] handlers
            = ServiceContextFactory.getInstance().getServiceContext(xletContext)
            .getServiceContentHandlers();
        for (int i = 0; i < handlers.length; i++) {
            if (handlers[i] instanceof Player) {
                playerList.add(handlers[i]);
            }
        }

        return playerList;
    }


    public static List players(final ServiceContext context,
                               final List players) {

        if (context == null) {
            throw new NullPointerException("null context");
        }

        if (players == null) {
            throw new NullPointerException("null players");
        }

        final ServiceContentHandler[] handlers
            = context.getServiceContentHandlers();
        for (int j = 0; j < handlers.length; j++) {
            if (handlers[j] instanceof Player) {
                players.add(handlers[j]);
            }
        }

        return players;
    }


    public static List players(final ServiceContext context) {

        return players(context, new ArrayList());
    }


    public static List players(final ServiceContextFactory factory,
                               final List players) {

        if (factory == null) {
            throw new NullPointerException("null factory");
        }

        if (players == null) {
            throw new NullPointerException("null players");
        }

        final ServiceContext[] contexts = factory.getServiceContexts();
        for (int i = 0; i < contexts.length; i++) {
            players(contexts[i], players);
        }

        return players;
    }


    public static List players(final ServiceContextFactory factory) {

        if (factory == null) {
            throw new NullPointerException("null factory");
        }

        return players(factory, new ArrayList());
    }


    public static List players() {

        return players(ServiceContextFactory.getInstance());
    }


    private Players() {

        super();
    }


}

