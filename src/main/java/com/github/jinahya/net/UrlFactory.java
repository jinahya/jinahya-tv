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


package com.github.jinahya.net;


import com.github.jinahya.util.UrlProperties;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class UrlFactory {


    public static final String RESOURCE_NAME = "base_urls.properties";


    private static final Map BASE_URLS;


    static {
        final Map baseUrls = new HashMap();
        final URL resource = UrlFactory.class.getResource(RESOURCE_NAME);
        if (resource == null) {
            throw new InstantiationError("resource not found: " + RESOURCE_NAME);
        }
        final UrlProperties properties = new UrlProperties();
        try {
            properties.load(resource);
        } catch (final IOException ioe) {
            throw new InstantiationError(ioe.getMessage());
        }
        for (final Iterator i = properties.entrySet().iterator();
             i.hasNext();) {
            final Entry e = (Entry) i.next();
            final String k = (String) e.getKey();
            final String v = (String) e.getValue();
        }
        BASE_URLS = Collections.unmodifiableMap(baseUrls);
    }


}

