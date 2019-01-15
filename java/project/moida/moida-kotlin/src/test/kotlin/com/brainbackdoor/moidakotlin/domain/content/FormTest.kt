package com.brainbackdoor.moidakotlin.domain.content

import com.brainbackdoor.moidakotlin.domain.member.Member
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class FormTest {
    @Autowired
    lateinit var formRepository: FormRepository

    @Before
    fun setUp() {
        val member = Member("bbd", "https://brainbackdoor.tistory.com")
        val form = Form(member = member)
        form.items.add(Item("두괄식으로 써야 한다.", false))
        form.items.add(Item("한 문단은 반드시 한 가지 생각만을 담아야 한다.", false))
        form.items.add(Item("접속사를 적극적으로 활용한다.", false))
        form.items.add(Item("가능하면 \"그것, 이것, 저것\" 등의 대명사를 사용하지 않는다.", false))
        form.items.add(Item("가능한 문장은 짧게 써야 한다.", false))
        formRepository.save(form)
    }

    @Test
    fun `H2 연동 테스트`() {
        val forms = formRepository.findAll()
        assertThat(forms[0].items[0].content).isEqualTo("두괄식으로 써야 한다.")
    }
}