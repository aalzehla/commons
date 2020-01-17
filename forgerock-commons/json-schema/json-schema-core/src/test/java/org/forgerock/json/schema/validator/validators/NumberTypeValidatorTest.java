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
 * Copyright 2011-2016 ForgeRock AS.
 */

package org.forgerock.json.schema.validator.validators;

import static org.testng.Assert.*;

import org.forgerock.json.schema.validator.CollectErrorsHandler;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

@SuppressWarnings("javadoc")
public class NumberTypeValidatorTest extends ValidatorTestBase {

    @DataProvider(name = "invalid-schema-objects")
    public Iterator<Object[]> invalidSchemaObject() throws Exception {
        List<Object[]> tests = getTestJSON("invalid", "/numberTests.json");
        return tests.iterator();
    }

    @DataProvider(name = "valid-schema-objects")
    public Iterator<Object[]> validSchemaObject() throws Exception {
        List<Object[]> tests = getTestJSON("valid", "/numberTests.json");
        return tests.iterator();
    }

    @Test(dataProvider = "valid-schema-objects")
    public void validateValidObjects(Validator validator, Object instance)  throws Exception {
        Assert.assertNotNull(validator);
        CollectErrorsHandler errorHandler = new CollectErrorsHandler();
        validator.validate(instance, null, errorHandler);
        assertFalse(errorHandler.hasError());
    }

    @Test(dataProvider = "invalid-schema-objects")
    public void validateInvalidObjects(Validator validator, Object instance)  throws Exception {
        Assert.assertNotNull(validator);
        CollectErrorsHandler errorHandler = new CollectErrorsHandler();
        validator.validate(instance, null, errorHandler);
        assertTrue(errorHandler.hasError());
    }
}
