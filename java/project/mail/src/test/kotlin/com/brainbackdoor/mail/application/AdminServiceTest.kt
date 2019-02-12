package com.brainbackdoor.mail.application

import com.brainbackdoor.mail.domain.Contents
import com.brainbackdoor.mail.domain.Receiver
import org.assertj.core.util.Lists
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class AdminServiceTest {
    @Autowired lateinit var adminSenrvice: AdminSenrvice

    @Test
    fun `메일보내기 테스트`() {
        val contents = Contents("주제", "내용")
        val receiver = Receiver("linl15@daum.net")
        adminSenrvice.sendMails(contents, Lists.newArrayList<Receiver>(receiver))
    }
}