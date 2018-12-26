package com.brainbackdoor.playgroundspring7;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /*
        DefaultFormattingConversionService
        FormatterRegistry나 ConverterRegistry를 ConversionService에 등록해 줌
        FormatterRegistry에 ConverterRegistry를 등록할 수 있다(상속받았기 때문)
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new EventFormatter());
    }
}
