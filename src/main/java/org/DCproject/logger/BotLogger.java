package org.DCproject.logger;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotLogger {
    public BotLogger() {

    }

    public void alert(LogLevel level, LogContext context, String message) {
        Logger logger = LoggerFactory.getLogger(context.toString());
        switch (level) {
            case INFO:
                logger.info(message);
                break;
            case WARNING:
                logger.warn(message);
                break;
            case ERROR:
                logger.error(message);
                break;
            default:
                logger.info(message);
                break;
        }
    }
}
