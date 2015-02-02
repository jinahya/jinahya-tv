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


package com.github.jinahya.util;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class UrlProperties extends Properties {


    private static final long serialVersionUID = 6794853027676078119L;


    public void load(final URL... urls) throws IOException {

        if (urls == null) {
            throw new NullPointerException("null urls");
        }

        for (final URL url : urls) {
            if (url == null) {
                throw new NullPointerException("urls[i] == null");
            }
            final InputStream stream = url.openStream();
            try {
                load(stream);
            } finally {
                stream.close();
            }
        }
    }


    public void load(final URL url) throws IOException {

        load(new URL[]{url});
    }


    public void load(final URL firstUrl, final URL secondUrl)
        throws IOException {

        load(new URL[]{firstUrl, secondUrl});
    }


    public void load(final URL firstUrl, final URL secondUrl,
                     final URL thirdUrl)
        throws IOException {

        load(new URL[]{firstUrl, secondUrl, thirdUrl});
    }


}

