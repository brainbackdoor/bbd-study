package com.brainbackdoor.mail.application

import com.brainbackdoor.mail.domain.Contents
import com.brainbackdoor.mail.domain.Receiver
import com.brainbackdoor.mail.infrastructure.Host
import com.brainbackdoor.mail.infrastructure.Mail
import com.brainbackdoor.mail.infrastructure.Sender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service

@Configuration
@ConfigurationProperties("mail")
class MailProperties {
    lateinit var hostAddress: String
    lateinit var senderId: String
    lateinit var senderPassword: String
}


@Service
class AdminSenrvice {

    @Autowired
    lateinit var mailProperties: MailProperties

    private fun sendMail(contents: Contents, receiver: Receiver) {
        val host = Host(mailProperties.hostAddress)
        val admin = Sender(mailProperties.senderId, mailProperties.senderPassword)
        admin.notice(Mail(host, contents), receiver)
    }

    fun sendMails(contents: Contents, receivers: List<Receiver>) =
            receivers.parallelStream().forEach { sendMail(contents, it) }
}