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


package com.github.jinahya.media;


import java.awt.Dimension;
import java.awt.Rectangle;
import javax.media.Controller;
import javax.tv.media.AWTVideoSize;
import javax.tv.media.AWTVideoSizeControl;
import org.dvb.media.BackgroundVideoPresentationControl;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class Controllers {


    public static <T> T getControl(final Controller controller,
                                   final Class<T> type) {

        if (controller == null) {
            throw new NullPointerException("null controller");
        }

        if (type == null) {
            throw new NullPointerException("null type");
        }

        return type.cast(controller.getControl(type.getName()));

    }


    public void resizeDvb(final Controller controller, final int width,
                          final int height) {

        if (controller == null) {
            throw new NullPointerException("null controller");
        }

//        final BackgroundVideoPresentationControl control
//            = (BackgroundVideoPresentationControl) controller.getControl(
//                BackgroundVideoPresentationControl.class.getName());
        final BackgroundVideoPresentationControl control
            = getControl(controller, BackgroundVideoPresentationControl.class);
    }


    /**
     *
     * @param controller
     *
     * @return
     *
     * @see #getControl(javax.media.Controller, java.lang.Class)
     */
    public static AWTVideoSizeControl getAWTVideoSizeControl(
        final Controller controller) {

        if (controller == null) {
            throw new NullPointerException("null controller");
        }

//        return (AWTVideoSizeControl) controller.getControl(
//            AWTVideoSizeControl.class.getName());
        return getControl(controller, AWTVideoSizeControl.class);
    }


    public boolean setSize(final Controller controller,
                           final Rectangle destination) {

        if (controller == null) {
            throw new NullPointerException("null controller");
        }

//        final AWTVideoSizeControl control
//            = (AWTVideoSizeControl) controller.getControl(
//                AWTVideoSizeControl.class.getName());
//        final AWTVideoSizeControl control
//            = getControl(controller, AWTVideoSizeControl.class);
        final AWTVideoSizeControl control = getAWTVideoSizeControl(controller);
        final Rectangle source = new Rectangle(control.getSourceVideoSize());
        final AWTVideoSize desired = new AWTVideoSize(source, destination);
        final AWTVideoSize checked = control.checkSize(desired);

        return control.setSize(checked);
    }


    public boolean setSize(final Controller controller, final Dimension size) {

        return setSize(controller, new Rectangle(size));
    }


    public boolean setSize(final Controller controller, final int width,
                           final int height) {

        return setSize(controller, new Dimension(width, height));
    }


    private Controllers() {

        super();
    }


}

