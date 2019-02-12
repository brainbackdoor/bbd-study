package com.brainbackdoor.mail.infrastructure

import com.brainbackdoor.mail.domain.Admin
import com.brainbackdoor.mail.domain.Contents
import com.brainbackdoor.mail.domain.Notification
import com.brainbackdoor.mail.domain.Receiver
import java.util.*
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import javax.mail.Authenticator
import javax.mail.Message.RecipientType

class Mail(
        private val host: Host,
        override val contents: Contents,
        var sender: Sender = Sender()
) : Notification {
    private val props: Properties = setProps()
    private val mail: MimeMessage = MimeMessage(getSession())

    override fun send(receiver: Receiver, sender: Admin) {
        this.sender = sender as Sender
        makeMail(receiver)
        Transport.send(mail)
    }

    private fun makeMail(receiver: Receiver) {
        mail.setFrom(InternetAddress(sender.id))
        mail.addRecipient(RecipientType.TO, InternetAddress(receiver.mailId))
        mail.subject = contents.subject
        mail.setText(contents.message)
    }

    private fun setProps(): Properties {
        val props = Properties()
        props["mail.smtp.host"] = host.address
        props["mail.smtp.port"] = 587
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.starttls.enable"] = "true"
        return props
    }

    private fun getSession() = Session.getDefaultInstance(props, object : Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication(sender.id, sender.password)
        }
    })
}

class Host(
        val address: String
)

class Sender(
        val id: String = "",
        val password: String = ""
) : Admin


