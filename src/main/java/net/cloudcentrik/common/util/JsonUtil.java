package net.cloudcentrik.common.util;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.eclipsesource.json.WriterConfig;

public class JsonUtil {

    public static void prettyPrint(JsonValue jsonObject){

        System.out.println(jsonObject.toString(WriterConfig.PRETTY_PRINT));
    }

    public static JsonObject wrapInLang(String propertyName,String propertyValue, String swedish, String english){
        JsonObject object=Json.object().add(propertyName,propertyValue)
                .add(propertyName.concat("_lang"),Json.object()
                        .add("sv",swedish).add("en",english));
        return object;
    }

}
