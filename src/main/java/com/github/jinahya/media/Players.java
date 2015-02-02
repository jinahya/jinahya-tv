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


import com.github.jinahya.tv.service.selection.ServiceContentHandlers;
import com.github.jinahya.util.function.Consumer;
import com.github.jinahya.util.function.Consumers;
import com.github.jinahya.util.function.Function;
import com.github.jinahya.util.function.Functions;
import com.github.jinahya.util.function.Predicate;
import com.github.jinahya.util.function.Predicates;
import javax.media.Player;
import javax.tv.service.selection.ServiceContentHandler;
import javax.tv.service.selection.ServiceContext;
import javax.tv.service.selection.ServiceContextException;
import javax.tv.service.selection.ServiceContextFactory;
import javax.tv.xlet.XletContext;


/**
 *
 * @author Jin Kwon &lt;jinahya at gmail.com&gt;
 */
public final class Players {


    /**
     * Supplies the result of
     * {@link ServiceContext#getServiceContenetHandlers()} invoke on specified
     * {@code context} to specified {@code consumer}.
     *
     * @param <T>
     * @param context
     * @param predicate
     * @param function
     * @param consumer
     */
    public static <T> void supply(
        final ServiceContext context, final Predicate<? super Player> predicate,
        final Function<Player, T> function,
        final Consumer<? super T> consumer) {

        ServiceContentHandlers.supply(
            context,
            Predicates.checkingInstanceOf(Player.class),
            Functions.<ServiceContentHandler, Player>casting(Player.class),
            Consumers.of(predicate, function, consumer)
        );
    }


    public static <T> void supply(final XletContext context,
                                  final Predicate<? super Player> predicate,
                                  final Function<Player, T> function,
                                  final Consumer<? super T> consumer)
        throws ServiceContextException {

        supply(ServiceContextFactory.getInstance().getServiceContext(context),
               predicate, function, consumer);
    }


    public static <T> void supply(final ServiceContextFactory factory,
                                  final Predicate<? super Player> predicate,
                                  final Function<Player, T> function,
                                  final Consumer<? super T> consumer) {
        if (factory == null) {
            throw new NullPointerException("null factory");
        }

        for (final ServiceContext context : factory.getServiceContexts()) {
            supply(context, predicate, function, consumer);
        }
    }


    private Players() {

        super();
    }


}

