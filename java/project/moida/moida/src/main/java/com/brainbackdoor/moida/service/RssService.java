package com.brainbackdoor.moida.service;

import com.brainbackdoor.moida.model.Member;
import com.brainbackdoor.moida.model.rss.Feed;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public class RssService {
    private static final String URI_SUFFIX = "/rss";

    public Feed request(Member member) throws IOException, FeedException {
        URL feedUrl = new URL(member.getBlogLink() + URI_SUFFIX);
        SyndFeed syndFeed = new SyndFeedInput().build(new XmlReader(feedUrl));
        return new Feed(member, syndFeed);
    }
}
