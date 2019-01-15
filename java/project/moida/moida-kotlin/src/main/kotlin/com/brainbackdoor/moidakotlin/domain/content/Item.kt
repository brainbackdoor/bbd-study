package com.brainbackdoor.moidakotlin.domain.content

import javax.persistence.*

@Entity
data class Item(

        val content: String,

        val confirm: Boolean,

        @Id @GeneratedValue
        val id: Long = 0
)