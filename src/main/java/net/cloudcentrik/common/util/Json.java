package net.cloudcentrik.common.util;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.eclipsesource.json.WriterConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static net.cloudcentrik.common.util.Json.JsonBuilder.JsonBuilder;


public class Json {

    JsonValue jsonValue;

    private Json(final String jsonString) {

        this.jsonValue = parse(jsonString);
    }

    private Json(final JsonValue jsonValue) {

        this.jsonValue = jsonValue;
    }

    public static Json Json() {
        return new Json(com.eclipsesource.json.Json.object().toString());
    }

    public static Json Json(final String jsonString) {
        return new Json(jsonString);
    }

    public static Json Json(final Consumer<JsonBuilder> builderConsumer) {
        final JsonBuilder jsonBuilder = JsonBuilder();
        builderConsumer.accept(jsonBuilder);
        return jsonBuilder.build();
    }

    public Json getJson(final String key) {
        if (getkeys().contains(key)) {
            return Json(thisJsonObject().get(key).toString());
        } else {
            return Json();
        }
    }

    public List<String> getkeys() {
        return getkeys(jsonValue);
    }

    public List<String> getkeys(final JsonValue jsonValue) {
        return asJsonObject(jsonValue).names();
    }

    public List<Json> getValues() {
        return getValues(jsonValue);
    }

    public List<Json> getValues(final JsonValue jsonValue) {
        return getkeys(jsonValue).stream()
                .map(key -> asJson(jsonValue).getJson(key))
                .collect(Collectors.toList());
    }

    public Map<String, Json> getAsJsonMap() {
        Map<String, Json> map = new HashMap<>();
        asJsonObject(jsonValue).names().forEach(key -> {
            map.put(key, asJson(jsonValue));
        });
        return map;
    }

    /*
    *TODO
     */
    public Map<String,JsonValue> getAsJsonValueMap() {
        Map<String, JsonValue> map = new HashMap<>();
        asJsonObject(jsonValue).names().forEach(key -> {
            /*if (getJson(key).jsonValue.isString()) {
                map.put(key,asJson(jsonValue).getJson(key).toString());
            } else if (getJson(key).jsonValue.isObject()) {
                map.put(key,asJson(jsonValue).getJson(key).asJsonObject());
            } else if (getJson(key).jsonValue.isArray()) {
                map.put(key,asJson(jsonValue).getJson(key).asJsonArray());
            }*/
            map.put(key,getJson(key).jsonValue);
        });
        return map;
    }

    public Map<String, String> getAsStringMap() {
        Map<String, String> map = new HashMap<>();
        asJsonObject(jsonValue).names().forEach(key -> {
            map.put(key, asJson(jsonValue).getJson(key).asString());
        });
        return map;
    }

    protected Json asJson(JsonValue jsonValue) {
        return Json(asJsonObject(jsonValue).toString());
    }

    protected JsonObject thisJsonObject() {
        return asJsonObject(jsonValue);
    }

    protected JsonArray thisJsonArray() {
        return asJsonArray(jsonValue);
    }

    protected JsonObject asJsonObject(JsonValue jsonValue) {
        if (jsonValue.isObject()) {
            return jsonValue.asObject();
        } else {
            return com.eclipsesource.json.Json.object();
        }
    }

    public JsonObject asJsonObject() {
        return asJsonObject(thisJsonObject());
    }

    public JsonObject asJsonObject(Json json) {
        return asJsonObject(json.thisJsonObject());
    }


    protected JsonArray asJsonArray(JsonValue jsonValue) {
        if (jsonValue.isArray()) {
            return jsonValue.asArray();
        } else {
            return com.eclipsesource.json.Json.array();
        }
    }

    public JsonArray asJsonArray(Json json) {
        return asJsonArray(json.thisJsonObject());
    }

    public JsonArray asJsonArray() {
        return asJsonArray(thisJsonArray());
    }


    public String asString() {
        return jsonValue.toString(WriterConfig.PRETTY_PRINT);
    }

    private static JsonValue parse(final String string) {
        return com.eclipsesource.json.Json.parse(string);
    }

    /*
     * JSON BUILDER
     */
    public static class JsonBuilder {

        JsonValue jsonValue;

        private JsonBuilder() {
            jsonValue = com.eclipsesource.json.Json.object();
        }

        private JsonBuilder(final String jsonString) {
            jsonValue = parse(jsonString);
        }

        protected static JsonBuilder JsonBuilder() {
            return new JsonBuilder();
        }

        protected static JsonBuilder JsonBuilder(final String jsonString) {
            return new JsonBuilder(jsonString);
        }

        protected Json build() {
            return new Json(jsonValue);
        }

        public JsonBuilder plus(final String key, final Json value) {
            jsonValue.asObject().set(key, value.jsonValue);
            return this;
        }

        public JsonBuilder plus(final String key, final Object value) {

            if (value instanceof String) {
                jsonValue.asObject().set(key, (String) value);
            } else if (value instanceof Integer) {
                jsonValue.asObject().set(key, (Integer) value);
            } else if (value instanceof Double) {
                jsonValue.asObject().set(key, (Double) value);
            } else if (value instanceof Boolean) {
                jsonValue.asObject().set(key, (Boolean) value);
            } else if (value instanceof JsonObject) {
                jsonValue.asObject().set(key, (JsonObject) value);
            } else if (value instanceof JsonArray) {
                jsonValue.asObject().set(key, (JsonArray) value);
            } else if (value instanceof JsonValue) {
                jsonValue.asObject().set(key, (JsonValue) value);
            }
            return this;
        }

        public <T> JsonBuilder plus(final String key, final List<T> values) {
            final JsonArray jsonArray = new JsonArray();
            values.forEach(value -> {
                if (value instanceof String) {
                    jsonArray.add((String) value);
                } else if (value instanceof Integer) {
                    jsonArray.add((Integer) value);
                } else if (value instanceof Double) {
                    jsonArray.add((Double) value);
                } else if (value instanceof Boolean) {
                    jsonArray.add((Boolean) value);
                } else if (value instanceof JsonObject) {
                    jsonArray.add((JsonObject) value);
                } else if (value instanceof JsonArray) {
                    jsonArray.add((JsonArray) value);
                } else if (value instanceof JsonValue) {
                    jsonArray.add((JsonValue) value);
                }
            });

            jsonValue.asObject().set(key, jsonArray);

            return this;
        }
/*
        public JsonBuilder plus(final Map<String, String> keyValues) {
            keyValues.forEach((key, value) -> {
                jsonValue.asObject().set(key, value);
            });
            return this;
        }*/

        public <T> JsonBuilder plus(final Map<String, T> keyValues) {
            keyValues.forEach((key, value) -> {
                if (value instanceof String) {
                    jsonValue.asObject().set(key, (String) value);
                } else if (value instanceof Integer) {
                    jsonValue.asObject().set(key, (Integer) value);
                } else if (value instanceof Double) {
                    jsonValue.asObject().set(key, (Double) value);
                } else if (value instanceof Boolean) {
                    jsonValue.asObject().set(key, (Boolean) value);
                } else if (value instanceof JsonObject) {
                    jsonValue.asObject().set(key, (JsonObject) value);
                } else if (value instanceof JsonArray) {
                    jsonValue.asObject().set(key, (JsonArray) value);
                } else if (value instanceof JsonValue) {
                    jsonValue.asObject().set(key, (JsonValue) value);
                }
            });
            return this;
        }

        public <T> JsonBuilder plus(final String name, final Map<String, T> keyValues) {
            final Json jsonMap = Json(json -> {
                json.plus(keyValues);
                /*keyValues.forEach((key, value) -> {
                    if (value instanceof String) {
                        json.plus(key, (String) value);
                    } else if (value instanceof Integer) {
                        json.plus(key, (Integer) value);
                    } else if (value instanceof Double) {
                        json.plus(key, (Double) value);
                    } else if (value instanceof Boolean) {
                        json.plus(key, (Boolean) value);
                    } else if (value instanceof JsonObject) {
                        json.plus(key, (JsonObject) value);
                    } else if (value instanceof JsonArray) {
                        json.plus(key, (JsonArray) value);
                    } else if (value instanceof JsonValue) {
                        json.plus(key, (JsonValue) value);
                    }
                });*/
            });

            jsonValue.asObject().set(name, jsonMap.asJsonObject());

            return this;
        }

        public JsonBuilder set(final String key, final Consumer<JsonBuilder> builderConsumer) {
            final JsonBuilder builder = JsonBuilder();
            builderConsumer.accept(builder);
            jsonValue.asObject().set(key, builder.jsonValue);
            return this;
        }
    }

}
