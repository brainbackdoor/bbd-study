package com.brainbackdoor.moida.controller;

import com.brainbackdoor.moida.model.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RssControllerTest {
    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockMvc;

    @Before
    public void setUp () {
        article = Article.builder().author("bbd").link("http://brainbackdoor.tistory.com/rss").build();
        map = new HashMap();
        map.put("article", article);
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    Map map;
    Article article;

    @Test
    public void getInit() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void getRss() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/rss")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(map))
        ).andExpect(status().isOk()).andDo(print());
    }
}