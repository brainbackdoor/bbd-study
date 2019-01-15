package com.brainbackdoor.moidakotlin.domain.content

import com.brainbackdoor.moidakotlin.domain.member.Member
import com.rometools.rome.feed.synd.SyndFeed
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import java.net.URL

class Feed(
        member: Member
) {
    var url: String = member.blogLink + "/rss"

    var syncFeed: SyndFeed = SyndFeedInput().build(XmlReader(URL(url)))
}
