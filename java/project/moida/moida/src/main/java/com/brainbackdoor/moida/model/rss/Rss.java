package com.brainbackdoor.moida.model.rss;

import com.brainbackdoor.moida.model.Member;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Rss {
    static ObjectMapper mapper = new ObjectMapper();

    public static Member buildFeedItems(Map<String, Object> model) throws Exception {
        return mapper
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(model), Member.class);
    }
}
