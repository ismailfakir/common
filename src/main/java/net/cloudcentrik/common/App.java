package net.cloudcentrik.common;

import ch.qos.logback.classic.Logger;
import net.cloudcentrik.common.logging.AppLogger;

public class App {
    private static final Logger log = AppLogger.getLogger(App.class.getName());

    public static void main(String[] args) throws Exception {
        /*log.info( "Hello World!" );
        log.info(""+timeStampNow());
        log.info(""+today());
        log.info(""+weekNumber());
        log.info(parseDateTime("2050-08-11 14:30:15",DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        log.info(parseToIsoDateTime("2050-08-11 14:30:15"));*/

        /*SystemConfiguration sysconf=SystemConfiguration.getInstance("config/app.properties");
        sysconf.addProperty("key","value");
        sysconf.setProperty("key","new value");
        log.info(sysconf.getDecryptedProperty("passwword"));*/

        /*Json json1=Json.Json(j->{ j
                .plus("json","json-test")
                .plus("id",10);
        });
        Json json2=Json.Json(j->{
            j.plus("json2","test2")
                    .plus("id","1006")
                    .plus("school",j1->{
                       j1.plus("entry",json1);
                    });
        });
        log.info(json2.asString());*/


    }
}
