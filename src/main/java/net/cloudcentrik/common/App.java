package net.cloudcentrik.common;

import ch.qos.logback.classic.Logger;
import net.cloudcentrik.common.logging.AppLogger;
import net.cloudcentrik.common.restclient.RestClient;

/**
 * Hello world!
 *
 */

public class App
{
    private static final Logger log = AppLogger.getLogger(App.class.getName());
    public static void main( String[] args ) throws Exception
    {
        log.info( "Hello World!" );
        RestClient.build("open","open123");
    }
}
