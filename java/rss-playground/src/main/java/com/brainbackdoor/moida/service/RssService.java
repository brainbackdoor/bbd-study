package com.brainbackdoor.moida.service;

import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service
public class RssService {

    public SyndEntry request(List<Item> items) throws IOException, FeedException {
        URL feedUrl = new URL(items.get(0).getLink());
        SyndFeed feed = new SyndFeedInput().build(new XmlReader(feedUrl));
        return feed.getEntries().get(0);
    }
}
