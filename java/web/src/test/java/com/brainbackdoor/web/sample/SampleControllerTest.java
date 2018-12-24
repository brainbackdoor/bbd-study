package com.brainbackdoor.web.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//실제로 Servlet Cotainer(내장톰캣)이 뜸
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//Servlet Container를 띄우지 않고, Servlet을 Mocking한 것을 뜸
//@AutoConfigureMockMvc
//DispatcherServlet이 Mockup에 요청하려면 이걸 추가 후 Autowired해야 한다.
public class SampleControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    public void hello() throws Exception {
//        mockMvc.perform(get("/hello"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("hello bbd"))
//                .andDo(print());
//    }

//    @Autowired
//    TestRestTemplate testRestTemplate;
//
//    @Test
//    public void hello() {
//        String result = testRestTemplate.getForObject("/hello", String.class);
//        assertThat(result).isEqualTo("hello bbd");
//    }
//
//    @MockBean// 이렇게 해야 Service까지 안감
//            SampleService sampleService;
//
//    @Test
//    public void helloMock() {
//        when(sampleService.getName()).thenReturn("bbd");
//        String result = testRestTemplate.getForObject("/hello", String.class);
//        assertThat(result).isEqualTo("hello bbd");
//    }

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    SampleService sampleService;
    /*
        add webflux dependency
     */
    @Test
    public void hello() {
        when(sampleService.getName()).thenReturn("bbd");

        webTestClient.get().uri("/hello").exchange().expectStatus().isOk()
                .expectBody(String.class).isEqualTo("hello bbd");
    }
}