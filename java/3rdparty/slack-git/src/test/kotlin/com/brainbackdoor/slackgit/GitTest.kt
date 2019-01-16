package com.brainbackdoor.slackgit

import org.junit.Test
import org.springframework.web.client.RestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType


class GitTest {

    val token = ""

    @Test
    fun `브랜치 생성하기`() {
        val attachment = Attachment()
        attachment.ref = "refs/heads/test"
        attachment.sha = "7c1bdd6c2179f44ff7d530bb851f0a6e77f706c2"

        val headers = HttpHeaders()
        headers.add("Authorization", "bearer $token")
        headers.contentType = MediaType.APPLICATION_JSON

        RestTemplate().exchange(
                Git.BOT.repoUrl,
                HttpMethod.POST,
                HttpEntity(attachment, headers),
                String::class.java)
    }
}