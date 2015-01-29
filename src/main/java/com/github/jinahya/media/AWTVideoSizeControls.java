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


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class AWTVideoSizeControls {


    /**
     * Finds the {@link AWTVideoSizeControl} instance within given
     * {@code control}.
     *
     * @param controller the controller.
     *
     * @return the found control or {@code null} if not found.
     *
     * @see Controllers#getControl(javax.media.Controller, java.lang.Class)
     */
    public static AWTVideoSizeControl get(final Controller controller) {

        return Controllers.getControl(controller, AWTVideoSizeControl.class);
    }


    public boolean resize(final Controller controller,
                          final Rectangle destination) {

        final AWTVideoSizeControl control = get(controller);
        if (control == null) {
            return false;
        }

        final Rectangle source = new Rectangle(control.getSourceVideoSize());

        final AWTVideoSize desired = new AWTVideoSize(source, destination);
        final AWTVideoSize checked = control.checkSize(desired);

        return control.setSize(checked);
    }


    public boolean setSize(final Controller controller, final Dimension size) {

        return resize(controller, new Rectangle(size));
    }


    public boolean setSize(final Controller controller, final int width,
                           final int height) {

        return setSize(controller, new Dimension(width, height));
    }


    private AWTVideoSizeControls() {

        super();
    }


}

