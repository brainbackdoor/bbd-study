package com.brainbackdoor.moida.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Item> buildFeedItems(Map<String, Object> model
            , HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(model.containsKey("article")) return Arrays.asList(setItem(model));
        return null;
    }

    private Item setItem(Map<String, Object> model) throws IOException {
        Article article = getArticle(model);
        Item item = new Item();
        item.setAuthor(article.getAuthor());
        item.setTitle(article.getTitle());
        item.setLink(article.getLink());
        return item;
    }

    private Article getArticle(Map<String, Object> model) throws IOException {
        return mapper
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(model.get("article")), Article.class);
    }
}
