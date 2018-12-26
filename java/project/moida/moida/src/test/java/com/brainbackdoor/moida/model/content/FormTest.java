package com.brainbackdoor.moida.model.content;

import com.brainbackdoor.moida.repository.FormRepository;
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
class FormTest {
    @Autowired
    FormRepository formRepository;

    @Test
    void findById() {
        List<Item> items = formRepository.findById(1L);
        assertThat(items.get(0).getContent(), is("두괄식으로 써야 한다."));
    }
}