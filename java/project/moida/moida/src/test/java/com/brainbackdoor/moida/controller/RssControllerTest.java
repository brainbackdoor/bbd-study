package com.brainbackdoor.moida.controller;

import com.brainbackdoor.moida.model.Member;
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



@SpringBootTest
@RunWith(SpringRunner.class)
public class RssControllerTest {
    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockMvc;

    @Before
    public void setUp () {
        member = Member.builder().name("bbd").blogLink("http://brainbackdoor.tistory.com").build();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    Member member;

    @Test
    public void getInit() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void getRss() throws Exception {
        Map<String, Object> map = new HashMap();
        map.put("name ", "bbd");
        map.put("blogLink", "https://brainbackdoor.tistory.com");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/rss")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(map))
        ).andExpect(status().isOk()).andDo(print());
    }
}