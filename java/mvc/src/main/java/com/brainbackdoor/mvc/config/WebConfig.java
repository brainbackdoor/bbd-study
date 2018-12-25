package com.brainbackdoor.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc -> 이걸 설정한다는 것은, 직접 WebMvcAutoConfiguration 여기서 설정하던걸
//                 직접 하겠다는 의미
public class WebConfig implements WebMvcConfigurer {
    // 추가로 설정하고 싶을때


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/m/**")
                .addResourceLocations("classpath:/m/")
                .setCachePeriod(20);
    }
}
