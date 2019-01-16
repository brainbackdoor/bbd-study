package com.brainbackdoor.moidakotlin.domain.content


import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class ItemTest {
    @Autowired lateinit var itemRepository: ItemRepository
    @Test
    fun `H2 연동 테스트` () {
        val item = Item("두괄식으로 써야 한다.", false)
        itemRepository.save(item)

        val byId = itemRepository.findById(item.id).get()
        assertThat(byId).isEqualTo(item)
    }
}