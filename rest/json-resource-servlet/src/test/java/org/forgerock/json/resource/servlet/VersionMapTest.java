/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2014 ForgeRock AS.
 */
package org.forgerock.json.resource.servlet;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class VersionMapTest {


    @Test(expectedExceptions = NullPointerException.class)
    public void nullInstanceWithBlankString() {
        // Given
        VersionMap.valueOf(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void handlesEmptyString() {
        // Given
        VersionMap.valueOf("");
    }

    @Test
    public void validApiVersionString() {

        // Given
        VersionMap versionMap = VersionMap.valueOf("resource=1.2");

        // Then
        assertNotNull(versionMap);
        assertNull(versionMap.getVersion(VersionType.CREST_API));
        assertEquals(versionMap.getVersion(VersionType.RESOURCE), "1.2");

        // Given
        versionMap = VersionMap.valueOf("api=2.1");

        // Then
        assertNotNull(versionMap);
        assertNull(versionMap.getVersion(VersionType.RESOURCE));
        assertEquals(versionMap.getVersion(VersionType.CREST_API), "2.1");

        // Given
        versionMap = VersionMap.valueOf("api=2.1; resource=1.2");

        // Then
        assertNotNull(versionMap);
        assertEquals(versionMap.getVersion(VersionType.RESOURCE), "1.2");
        assertEquals(versionMap.getVersion(VersionType.CREST_API), "2.1");

        // Given
        versionMap = VersionMap.valueOf("resource=1.2; api=2.1");

        // Then
        assertNotNull(versionMap);
        assertEquals(versionMap.getVersion(VersionType.RESOURCE), "1.2");
        assertEquals(versionMap.getVersion(VersionType.CREST_API), "2.1");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void invalidDelimiter() {
        // Given
        VersionMap.valueOf("resource=1.2, api=2.1");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void invalidVersionSchema() {
        // Given
        VersionMap.valueOf("resource=1.2.3");
    }

}