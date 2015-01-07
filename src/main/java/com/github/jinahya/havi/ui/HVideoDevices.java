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


package com.github.jinahya.havi.ui;


import java.awt.Rectangle;
import org.davic.resources.ResourceClient;
import org.havi.ui.HConfigurationException;
import org.havi.ui.HPermissionDeniedException;
import org.havi.ui.HScreen;
import org.havi.ui.HScreenConfigTemplate;
import org.havi.ui.HVideoConfigTemplate;
import org.havi.ui.HVideoConfiguration;
import org.havi.ui.HVideoDevice;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class HVideoDevices {


    public static boolean resize(final Rectangle destination,
                                 final ResourceClient client)
        throws HPermissionDeniedException, HConfigurationException {

        final HScreen screen = HScreen.getDefaultHScreen();
        final HVideoDevice device = screen.getDefaultHVideoDevice();
        if (device.reserveDevice(client)) {
            return false;
        }
        final HVideoConfigTemplate template = new HVideoConfigTemplate();
        template.setPreference(HScreenConfigTemplate.SCREEN_RECTANGLE,
                               destination, HScreenConfigTemplate.PREFERRED);
        final HVideoConfiguration configuration
            = device.getBestConfiguration(template);
        if (configuration == null) {
            return false;
        }

        device.setVideoConfiguration(configuration);

        return true;
    }


    private HVideoDevices() {

        super();
    }


}

