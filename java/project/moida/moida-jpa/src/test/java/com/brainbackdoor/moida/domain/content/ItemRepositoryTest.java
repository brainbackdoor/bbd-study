package com.brainbackdoor.moida.domain.content;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository repository;

    @Test
    public void crud() {
        Item item = new Item();
        item.setContent("Moida Project Item Contents");
        item.setConfirm(true);

        repository.save(item);
        Optional<Item> byId = repository.findById(item.getId());
        assertThat(byId.get()).isEqualTo(item);
    }
}