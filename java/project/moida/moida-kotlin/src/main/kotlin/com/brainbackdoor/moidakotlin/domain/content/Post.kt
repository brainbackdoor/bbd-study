package com.brainbackdoor.moidakotlin.domain.content

import com.brainbackdoor.moidakotlin.domain.member.Member
import javax.persistence.*

@Entity
data class Post(
        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(foreignKey = ForeignKey(name = "fk_post_to_member"))
        val member: Member,

        val title: String,

        @Column(columnDefinition = "TEXT")
        val description: String,

        @Id @GeneratedValue
        val id: Long = 0
)