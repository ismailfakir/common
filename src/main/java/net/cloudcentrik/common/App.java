package net.cloudcentrik.common;

import ch.qos.logback.classic.Logger;
import net.cloudcentrik.common.logging.AppLogger;
import net.cloudcentrik.common.restclient.RestClient;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static net.cloudcentrik.common.util.TimeUtils.*;

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
        //RestClient.build("open","open123");
        log.info(""+timeStampNow());
        log.info(""+today());
        log.info(""+weekNumber());
        log.info(parseDateTime("2050-08-11 14:30:15",DateTimeFormatter.ISO_DATE_TIME));
        log.info(parseToIsoDateTime("2050-08-11 14:30:15"));
    }
}
