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
 * Copyright 2015 ForgeRock AS.
 */

package org.forgerock.http.header;

import org.forgerock.http.protocol.Header;

/**
 * Thrown when a header string cannot be parsed to a rich {@link Header} implementation.
 */
public class MalformedHeaderException extends Exception {

    /**
     * Constructs a new exception with the given message.
     * @param message The message.
     */
    public MalformedHeaderException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the given message and cause.
     * @param message The message.
     * @param cause The cause.
     */
    public MalformedHeaderException(String message, Exception cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the given cause.
     * @param cause The cause.
     */
    public MalformedHeaderException(Exception cause) {
        super(cause);
    }

}
