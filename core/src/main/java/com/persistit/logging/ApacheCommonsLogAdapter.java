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

import org.apache.commons.logging.Log;

/**
 * Wraps an <code>org.apache.commons.logging.Log</code> for Persistit logging.
 * Code to enable default logging through Apache commons logging is shown here:
 * <code><pre>
 *    // refer to any appropriate org.apache.commons.logging.Log, for example
 *    Log log = LogFactory.getLog(getClass());
 *    Persistit.setPersistitLogger(new ApacheCommonsAdapter(logger));
 * </pre></code>
 * 
 * @version 1.1
 */
public class ApacheCommonsLogAdapter implements PersistitLogger {
    /**
     * The Log object wrapped by this adapter.
     */
    private Log _logger;

    /**
     * Wraps an Apache commons <code>Log</code> so that Persistit can write to
     * it.
     * 
     * @param log
     *            A <code>Log</code> to which Persistit log messages will be
     *            directed.
     */
    public ApacheCommonsLogAdapter(Log log) {
        _logger = log;
    }

    /**
     * Overrides <code>isLoggable</code> to allow control by the wrapped
     * <code>Logger</code>.
     * 
     * @param level
     *            The <code>level</code>
     */
    @Override
    public boolean isLoggable(PersistitLevel level) {
        switch (level) {
        case NONE:
            return false;
        case TRACE:
            return _logger.isTraceEnabled();
        case DEBUG:
            return _logger.isDebugEnabled();
        case INFO:
            return _logger.isInfoEnabled();
        case WARNING:
            return _logger.isWarnEnabled();
        case ERROR:
            return _logger.isErrorEnabled();
        default:
            throw new RuntimeException("base switch");
        }
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
        switch (level) {
        case NONE:
            break;
        case TRACE:
            _logger.trace(message);
            break;
        case DEBUG:
            _logger.debug(message);
            break;
        case INFO:
            _logger.info(message);
            break;
        case WARNING:
            _logger.warn(message);
            break;
        case ERROR:
            _logger.error(message);
            break;
        default:
            throw new RuntimeException("base switch");
        }
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
