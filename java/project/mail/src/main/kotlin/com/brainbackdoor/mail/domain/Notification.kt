package com.brainbackdoor.mail.domain


interface Notification {
    val contents: Contents
    fun send(receiver: Receiver, sender: Admin)
}

class Contents(
        val subject: String,
        val message: String
)

class Receiver(
        val mailId: String = ""
)