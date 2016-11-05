/*
 * CrossOriginResourceSharingFilterTest.java
 *
 * Copyright (c) 2016, Tobias Koltsch. All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/lgpl.txt>.
 */
package org.nerdcoding.sample.angular2.server.web.authentication;

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
