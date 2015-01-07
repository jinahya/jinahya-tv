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


package com.github.jinahya.dvb.media;


import com.github.jinahya.media.Controllers;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.media.Controller;
import org.dvb.media.BackgroundVideoPresentationControl;
import org.dvb.media.VideoTransformation;
import org.havi.ui.HScreenPoint;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class BackgroundVideoPresentationControls {


    public static BackgroundVideoPresentationControl get(
        final Controller controller) {

        return Controllers.getControl(
            controller, BackgroundVideoPresentationControl.class);
    }


    public boolean resize(final Controller controller,
                          final Rectangle destination) {

        final BackgroundVideoPresentationControl control = get(controller);
        if (control == null) {
            return false;
        }

        final Dimension source = control.getInputVideoSize();

        final float horizontalScalingFactor
            = destination.width / (float) source.width;
        final float verticalScalingFactor
            = destination.height / (float) source.height;
        final HScreenPoint location = new HScreenPoint(
            destination.x / source.width, destination.y / source.height);

        final VideoTransformation desired = new VideoTransformation(
            null, horizontalScalingFactor, verticalScalingFactor, location);
        final VideoTransformation checked = control.getClosestMatch(desired);

        control.setVideoTransformation(checked);

        return true;
    }


    private BackgroundVideoPresentationControls() {

        super();
    }


}

