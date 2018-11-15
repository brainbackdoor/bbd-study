package com.brainbackdoor.moida.model.rss;

import com.brainbackdoor.moida.model.Member;
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
        if(model.containsKey("member")) return Arrays.asList(setItem(model));
        return null;
    }

    private Item setItem(Map<String, Object> model) throws IOException {
        Member member = getArticle(model);
        Item item = new Item();
        item.setAuthor(member.getName());
        item.setTitle(member.getTitle());
        item.setLink(member.getBlogLink());
        return item;
    }

    private Member getArticle(Map<String, Object> model) throws IOException {
        return mapper
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(model.get("member")), Member.class);
    }
}
