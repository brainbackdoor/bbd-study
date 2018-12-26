package com.brainbackdoor.moida.model.content;

import com.brainbackdoor.moida.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CheckListTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    void getCheckList() {
        List<Item> items = itemRepository.findById(1L);


    }
}