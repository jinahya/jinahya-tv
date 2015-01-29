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


import com.github.jinahya.tv.service.selection.ServiceMediaHandlers;
import java.util.Collection;
import java.util.List;
import javax.media.Player;
import javax.tv.service.selection.ServiceContentHandler;
import javax.tv.service.selection.ServiceContext;
import javax.tv.service.selection.ServiceContextException;
import javax.tv.service.selection.ServiceContextFactory;
import static javax.tv.service.selection.ServiceContextFactory.getInstance;
import javax.tv.xlet.XletContext;


/**
 *
 * @author Jin Kwon &lt;jinahya at gmail.com&gt;
 */
public final class Players {


    public static Collection<? super Player> collect(final ServiceContext context,
                                                     final Collection<? super Player> collection, final int limit) {

        return ServiceMediaHandlers.collect(context, Player.class, collection, limit);
    }


    /**
     * Returns a list of {@link ServiceContentHandler} related to given
     * {@code xletContext} which each is an instance of {@link Player}.
     *
     * @param context the xlet context
     * @param collection the list which players are added and returned.
     *
     * @return a list of {@link ServiceContentHandler}s which each is an
     * instance of {@link Player}.
     *
     * @throws ServiceContextException
     * @see ServiceContextFactory#getServiceContext(javax.tv.xlet.XletContext)
     * @see #list(javax.tv.service.selection.ServiceContext, java.util.List)
     */
    public static List<Player> list(final XletContext context,
                                    final List<Player> collection)
        throws ServiceContextException {

        if (context == null) {
            throw new NullPointerException("null context");
        }

        return list(getInstance().getServiceContext(context), collection);
    }


    public static List<Player> list(final ServiceContext context,
                                    final List<Player> players) {

        if (context == null) {
            throw new NullPointerException("null context");
        }

        if (players == null) {
            throw new NullPointerException("null players");
        }

        for (final ServiceContentHandler handler
             : context.getServiceContentHandlers()) {
            if (handler instanceof Player) {
                players.add((Player) handler);
            }
        }

        return players;
    }


    /**
     * Returns a list of {@link ServiceContentHandler} related to given
     * {@code xletContext} which each is an instance of {@link Player}.
     *
     * @param context the xlet context
     * @param collection the list which players are added and returned.
     *
     * @return a list of {@link ServiceContentHandler}s which each is an
     * instance of {@link Player}.
     *
     * @throws ServiceContextException
     * @see ServiceContextFactory#getServiceContext(javax.tv.xlet.XletContext)
     * @see #list(javax.tv.service.selection.ServiceContext, java.util.List)
     */
    public static List<Player> list(final XletContext context,
                                    final List<Player> collection)
        throws ServiceContextException {

        if (context == null) {
            throw new NullPointerException("null context");
        }

        return list(getInstance().getServiceContext(context), collection);
    }


    /**
     *
     * @param factory
     * @param players
     *
     * @return given {@code players}
     *
     * @see ServiceContextFactory#getServiceContexts()
     * @see #list(javax.tv.service.selection.ServiceContext, java.util.List)
     */
    public static List<Player> list(final ServiceContextFactory factory,
                                    final List<Player> players) {

        if (factory == null) {
            throw new NullPointerException("null factory");
        }

        for (final ServiceContext context : factory.getServiceContexts()) {
            list(context, players);
        }

        return players;
    }


    private Players() {

        super();
    }


}

