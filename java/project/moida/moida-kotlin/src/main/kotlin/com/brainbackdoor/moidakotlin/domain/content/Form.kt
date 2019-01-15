package com.brainbackdoor.moidakotlin.domain.content

import com.brainbackdoor.moidakotlin.domain.member.Member
import javax.persistence.*

@Entity
data class Form(
        @OneToMany(cascade = [CascadeType.ALL])
        val items: MutableList<Item> = mutableListOf(),

        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "form")
        @JoinColumn(name = "FORM_ID")
        val member: Member,

        @Id @GeneratedValue
        val id: Long = 0
)