package net.cloudcentrik.common;

import ch.qos.logback.classic.Logger;
import net.cloudcentrik.common.logging.AppLogger;

/**
 * Hello world!
 *
 */

public class App
{
    private static final Logger log = AppLogger.getLogger(App.class.getName());
    public static void main( String[] args )
    {
        log.info( "Hello World!" );
    }
}
