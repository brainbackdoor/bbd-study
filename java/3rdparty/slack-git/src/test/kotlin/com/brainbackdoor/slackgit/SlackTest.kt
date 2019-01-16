package com.brainbackdoor.slackgit

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate

class SlackTest {

    val slackIcon = ":slack:"
    val mapper = ObjectMapper()

    @Test
    fun `슬랙 메시지 보내기`() {
        val attachment = Attachment()
        attachment.text = "$slackIcon test"
        val response = RestTemplate()
                .postForEntity(
                        Slack.BOT.hookUrl,
                        mapper.writeValueAsString(attachment),
                        String::class.java)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }
}