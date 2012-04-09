/**
 * END USER LICENSE AGREEMENT (“EULA”)
 *
 * READ THIS AGREEMENT CAREFULLY (date: 9/13/2011):
 * http://www.akiban.com/licensing/20110913
 *
 * BY INSTALLING OR USING ALL OR ANY PORTION OF THE SOFTWARE, YOU ARE ACCEPTING
 * ALL OF THE TERMS AND CONDITIONS OF THIS AGREEMENT. YOU AGREE THAT THIS
 * AGREEMENT IS ENFORCEABLE LIKE ANY WRITTEN AGREEMENT SIGNED BY YOU.
 *
 * IF YOU HAVE PAID A LICENSE FEE FOR USE OF THE SOFTWARE AND DO NOT AGREE TO
 * THESE TERMS, YOU MAY RETURN THE SOFTWARE FOR A FULL REFUND PROVIDED YOU (A) DO
 * NOT USE THE SOFTWARE AND (B) RETURN THE SOFTWARE WITHIN THIRTY (30) DAYS OF
 * YOUR INITIAL PURCHASE.
 *
 * IF YOU WISH TO USE THE SOFTWARE AS AN EMPLOYEE, CONTRACTOR, OR AGENT OF A
 * CORPORATION, PARTNERSHIP OR SIMILAR ENTITY, THEN YOU MUST BE AUTHORIZED TO SIGN
 * FOR AND BIND THE ENTITY IN ORDER TO ACCEPT THE TERMS OF THIS AGREEMENT. THE
 * LICENSES GRANTED UNDER THIS AGREEMENT ARE EXPRESSLY CONDITIONED UPON ACCEPTANCE
 * BY SUCH AUTHORIZED PERSONNEL.
 *
 * IF YOU HAVE ENTERED INTO A SEPARATE WRITTEN LICENSE AGREEMENT WITH AKIBAN FOR
 * USE OF THE SOFTWARE, THE TERMS AND CONDITIONS OF SUCH OTHER AGREEMENT SHALL
 * PREVAIL OVER ANY CONFLICTING TERMS OR CONDITIONS IN THIS AGREEMENT.
 */

package com.persistit.logging;

import java.util.EnumMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Wraps a JDK 1.4 <code>java.util.logging.Logger</code> for Persistit logging.
 * Code to enable default logging through the JDK 1.4 Logging API is shown here:
 * <code><pre>
 *    // refer to any appropriate java.util.logging.Logger, for example
 *    Logger logger = java.util.logging.Logger.getLogger("com.persistit");    //(for example)
 *    Persistit.setPersistitLogger(new JDK14LoggingAdapter(logger));
 * </pre></code>
 * 
 * @version 1.1
 */
public class JDK14LoggingAdapter implements PersistitLogger {

    private final static EnumMap<PersistitLevel, Level> LEVEL_MAP = new EnumMap<PersistitLevel, Level>(
            PersistitLevel.class);

    static {
        LEVEL_MAP.put(PersistitLevel.NONE, Level.OFF);
        LEVEL_MAP.put(PersistitLevel.TRACE, Level.FINER);
        LEVEL_MAP.put(PersistitLevel.DEBUG, Level.FINE);
        LEVEL_MAP.put(PersistitLevel.INFO, Level.INFO);
        LEVEL_MAP.put(PersistitLevel.WARNING, Level.WARNING);
        LEVEL_MAP.put(PersistitLevel.ERROR, Level.SEVERE);
    }

    private final Logger _logger;

    /**
     * Constructs a wrapped JDK 1.4 Logger.
     * 
     * @param logger
     *            A <code>Logger</code> to which Persistit log messages will be
     *            directed.
     */
    public JDK14LoggingAdapter(Logger logger) {
        _logger = logger;
    }

    /**
     * Overrides <code>isLoggable</code> to allow control by the wrapped
     * <code>Logger</code>.
     * 
     * @param lt
     *            The <code>LogTemplate</code>
     */
    @Override
    public boolean isLoggable(PersistitLevel level) {
        return _logger.isLoggable(LEVEL_MAP.get(level));
    }

    /**
     * Writes a log message generated by Persistit to the wrapped
     * <code>Logger</code>.
     * 
     * @param level
     *            The <code>PersistitLevel</code>
     * @param message
     *            The message to write to the log.
     */
    @Override
    public void log(PersistitLevel level, String message) {
        _logger.log(LEVEL_MAP.get(level), message);
    }

    @Override
    public void open() {
        // Nothing to do - the log is created and destroyed by the embedding
        // application
    }

    @Override
    public void close() {
        // Nothing to do - the log is created and destroyed by the embedding
        // application
    }


    @Override
    public void flush() {
        // Nothing to do - log output is managed by the embedding
        // application
    }
}
