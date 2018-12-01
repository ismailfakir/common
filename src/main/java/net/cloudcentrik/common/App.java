package net.cloudcentrik.common;

import ch.qos.logback.classic.Logger;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import net.cloudcentrik.common.logging.AppLogger;
import net.cloudcentrik.common.util.Json;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        /*log.info( "Hello World!" );
        //RestClient.build("open","open123");
        log.info(""+timeStampNow());
        log.info(""+today());
        log.info(""+weekNumber());
        log.info(parseDateTime("2050-08-11 14:30:15",DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        log.info(parseToIsoDateTime("2050-08-11 14:30:15"));*/

        Map<String,Object> map=new LinkedHashMap<>();
        map.put("Name","ismail");
        map.put("id",1001);
        map.put("fee paid",true);

        List<Object> numbers=new ArrayList<>();
        numbers.add(10);
        numbers.add(15);
        numbers.add(20);

        Json json=Json.Json(j->{ j
                .plus("Student",map)
                .plus("t2","test2")
                .plus("numbers",numbers)
                .plus("id",10);
        });

        //log.info(json.asString());
/*
        Json json2=Json.Json(j->{
            j.plus("t2","test2").plus("t2","test2")
                    .plus("json",json)
                    .set("test",j1->{
                       j1.plus("the","#");
                    });
        });

        JsonObject jsonObject=new JsonObject();
        jsonObject.set("1","one");
        jsonObject.set("2","two");

        JsonObject jsonObject1=new JsonObject();
        jsonObject1.set("1","one");
        jsonObject1.set("2","two");
        jsonObject1.set("two",jsonObject);*/


        //log.info(jsonObject.toString());
        //log.info(jsonObject1.get("two").toString());

        List<Json> list=json.getValues();
        //list.forEach(e->log.info(e.asString()));
        //log.info(l.get(2).asString());

        Map<String,JsonValue> student=json.getAsJsonValueMap();
        JsonArray array=(JsonArray) student.get("numbers");
        array.forEach(e->log.info(e+""));

    }
}
