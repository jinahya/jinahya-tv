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
import javax.tv.service.selection.ServiceContextException;
import javax.tv.service.selection.ServiceMediaHandler;
import javax.tv.xlet.XletContext;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class ServiceMediaHandlers {


    public static <T extends ServiceMediaHandler> Collection<? super T> collect(
        final ServiceContext context, final Class<T> type,
        final Collection<? super T> collection, final int limit) {

        return ServiceContentHandlers.collect(context, type, collection, limit);
    }


    public static <T extends ServiceMediaHandler> Collection<? super T> collect(
        final XletContext context, final Class<T> type,
        final Collection<? super T> collection, final int limit)
        throws ServiceContextException {

        return ServiceContentHandlers.collect(context, type, collection, limit);
    }


    private ServiceMediaHandlers() {

        super();
    }


}

