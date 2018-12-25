package com.brainbackdoor.thymeleaf;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
public class SampleControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    public void hello() throws Exception {
//        // 요청 "/hello"
//        // 응답
//        // - modeal name : bbd
//        // - view name : hello
//        mockMvc.perform(get("/hello"))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(view().name("hello"))
//                .andExpect(model().attribute("name", "bbd"))
//                .andExpect(content().string(containsString("bbd")));
//    }

    @Autowired
    WebClient webClient;

    @Test
    public void hello() throws IOException {
        HtmlPage page = webClient.getPage("/hello");
        HtmlHeading1 h1 = page.getFirstByXPath("//h1");
        assertThat(h1.getTextContent()).isEqualToIgnoringCase("bbd");
    }
}