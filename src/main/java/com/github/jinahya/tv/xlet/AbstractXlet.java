/*
 * Copyright 2015 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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


package com.github.jinahya.tv.xlet;


import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public abstract class AbstractXlet implements Xlet {


    @Override
    public void initXlet(final XletContext ctx)
        throws XletStateChangeException {

        XletContextHolder.setXletContext(ctx);
    }


    @Override
    public void startXlet() throws XletStateChangeException {

    }


    @Override
    public void pauseXlet() {

    }


    @Override
    public void destroyXlet(boolean unconditional)
        throws XletStateChangeException {

        XletContextHolder.setXletContext(null);
    }


}

