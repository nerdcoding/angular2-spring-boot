/*
 * CrossOriginResourceSharingFilterTest.java
 *
 * Copyright (c) 2016, Tobias Koltsch. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.nerdcoding.sample.angular2.server.web.security;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import org.junit.Test;

/**
 * JUnit test for {@link CrossOriginResourceSharingFilter}.
 *
 * @author Tobias Koltsch
 * @version 1.0.0
 */
public class CrossOriginResourceSharingFilterTest {

    private CrossOriginResourceSharingFilter subject = new CrossOriginResourceSharingFilter();

    @Test
    public void testSwitchLocalhost() {
        assertEquals("http://192.168.1.1", subject.switchLocalhost("http://192.168.1.1"));
        assertEquals("http://192.168.1.1:1234/abc", subject.switchLocalhost("http://192.168.1.1:1234/abc"));
        assertEquals("http://127.0.0.1/", subject.switchLocalhost("http://127.0.0.1/"));
        assertEquals("http://127.0.0.1/", subject.switchLocalhost("http://localhost/"));
        assertEquals("http://127.0.0.1", subject.switchLocalhost("http://localhost"));
        assertEquals("http://127.0.0.1:1234/", subject.switchLocalhost("http://localhost:1234/"));
        assertEquals("http://127.0.0.1:1234/", subject.switchLocalhost("http://LOCALHOST:1234/"));
        assertEquals("http://127.0.0.1:1234/", subject.switchLocalhost("http://LoCaLhOsT:1234/"));
    }

    @Test
    public void testOriginEqualsClientUrl() {
        assertTrue(subject.originEqualsClientUrl("http://192.168.1.1", "http://192.168.1.1"));
        assertTrue(subject.originEqualsClientUrl("http://192.168.1.1/", "http://192.168.1.1"));
        assertTrue(subject.originEqualsClientUrl("http://LOCALHOST:1234/", "http://127.0.0.1:1234"));
        assertTrue(subject.originEqualsClientUrl("http://localhost:1234/", "http://127.0.0.1:1234"));
        assertTrue(subject.originEqualsClientUrl("http://LoCaLhOsT:1234", "http://127.0.0.1:1234"));

        assertFalse(subject.originEqualsClientUrl("http://192.168.1.1:1234", "http://127.0.0.1:1234"));
    }

}
