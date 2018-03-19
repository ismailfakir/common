package net.cloudcentrik.common.logging;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import org.slf4j.LoggerFactory;

public class AppLogger {

    private static AppLogger instance = null;

    public static Logger rootLogger=null;

    private AppLogger(String className) {
        rootLogger = (Logger) LoggerFactory.getLogger(className);
        LoggerContext loggerContext = rootLogger.getLoggerContext();
        // we are not interested in auto-configuration
        loggerContext.reset();

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(loggerContext);
        encoder.setPattern("%d{HH:mm:ss.SSS} %cyan([%logger{15}).%red(%M %L]) %highlight(%-5level): %msg %n");
        encoder.start();

        ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<ILoggingEvent>();
        appender.setContext(loggerContext);
        appender.setEncoder(encoder);
        appender.start();

        rootLogger.addAppender(appender);
    }

    public static Logger getLogger(String className) {
        if(instance == null) {
            instance = new AppLogger(className);
            return rootLogger;
        }
        return rootLogger;
    }

}
