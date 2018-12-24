package com.brainbackdoor.web.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
public class SampleController3rdTest {
    /*
    use the @WebMvcTest annotation.
    @WebMvcTest auto-configures the Spring MVC infrastructure and limits scanned beans to
    @Controller,
    @ControllerAdvice,
    @JsonComponent, @Converter, @GenericConverter, Filter, WebMvcConfigurer,
    and HandlerMethodArgumentResolver

    so Service is added by mock
     */
    @MockBean
    SampleService sampleService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello() throws Exception {
        when(sampleService.getName()).thenReturn("bbd");

        mockMvc.perform(get("/hello"))
                .andExpect(content().string("hello bbd"));
    }
}
