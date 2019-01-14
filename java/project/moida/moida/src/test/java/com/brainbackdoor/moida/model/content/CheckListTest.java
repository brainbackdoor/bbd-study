package com.brainbackdoor.moida.model.content;

import com.brainbackdoor.moida.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.brainbackdoor.moida.model.content.Item;
import java.util.List;


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