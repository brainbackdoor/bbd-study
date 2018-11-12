package com.brainbackdoor.moida.model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rometools.rome.feed.rss.Item;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class RssFeed extends AbstractRssFeedView {

    @Override
    public List<Item> buildFeedItems(Map<String, Object> model
            , HttpServletRequest request, HttpServletResponse response) throws Exception {
        return Arrays.asList(mapping(model));
    }

    private static Item mapping(Map model) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(model), Item.class);
    }

}
