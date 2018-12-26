package com.brainbackdoor.json;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Loader {
    static final Map<String, Schema> _schemas;

    static {
        Map<String, Schema> schemas = new HashMap<>();
        Arrays.stream(EventSchema.values()).forEach(v -> {
            JSONObject jsonSchema = new JSONObject(new JSONTokener(Loader.class.getResourceAsStream(v.path)));
            Schema schema = SchemaLoader.load(jsonSchema);
            schemas.put(v.name, schema);
        });
        _schemas = Collections.unmodifiableMap(schemas);
    }

    enum EventSchema {
        BASEPROPERTIES("BaseProperties", "schemaBase.json"),
        PAGEVIEW("Pageview", "schemaPageview.json");

        String name;
        String path;

        EventSchema(String name, String path) {
            this.name = name;
            this.path = "/json/event/" + path;
        }
    }
}
