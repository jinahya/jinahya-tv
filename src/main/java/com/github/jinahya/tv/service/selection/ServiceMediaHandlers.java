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


import java.util.List;
import javax.tv.service.selection.ServiceContextException;
import javax.tv.service.selection.ServiceContextFactory;
import javax.tv.service.selection.ServiceMediaHandler;
import javax.tv.xlet.XletContext;


/**
 *
 * @author Jin Kwon &lt;jinahya at gmail.com&gt;
 */
public final class ServiceMediaHandlers {


    public static List list(final XletContext xletContext, final List list)
        throws ServiceContextException {

        if (xletContext == null) {
            throw new NullPointerException("null xletContext");
        }

        ServiceContexts.handlers(
            ServiceContextFactory.getInstance().getServiceContext(xletContext),
            ServiceContexts.HandlerPredicates.instanceOfAny(
                new Class[]{ServiceMediaHandler.class}),
            ServiceContexts.HandlerConsumers.collecting(list));

        return list;
    }


    private ServiceMediaHandlers() {

        super();
    }


}

