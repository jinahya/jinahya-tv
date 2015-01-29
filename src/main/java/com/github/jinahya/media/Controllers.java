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


import javax.media.Controller;


/**
 * A utility class for {@link Controller}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class Controllers {


    /**
     * Finds the control of given {@code type} from specified
     * {@code controller}.
     *
     * @param <T> control type parameter
     * @param controller the controller.
     * @param type the control type.
     *
     * @return found control or {@code null} if not found.
     *
     * @see Controller#getControl(java.lang.String)
     */
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


    private Controllers() {

        super();
    }


}

