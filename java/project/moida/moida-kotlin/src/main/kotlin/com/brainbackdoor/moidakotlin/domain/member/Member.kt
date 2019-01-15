package com.brainbackdoor.moidakotlin.domain.member

import com.brainbackdoor.moidakotlin.domain.content.Form
import javax.persistence.*

@Entity
data class Member(
        val name: String,
        val blogLink: String,

        @Id @GeneratedValue
        val id: Long = 0
) {
        @OneToOne(fetch = FetchType.LAZY)
        lateinit var form: Form
}