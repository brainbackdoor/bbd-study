package com.brainbackdoor.mail.domain

interface Admin {
    fun notice(notification: Notification, receiver: Receiver) {
        notification.send(receiver, this)
    }
}