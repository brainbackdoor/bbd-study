package com.brainbackdoor.playgroundspring7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    ConversionService conversionService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(conversionService.getClass().toGenericString());
        // WebConversionService은 DefaultFormattingConversionService를 상속받아 확장하여 구현
//        conversionService.convert()
    }
}
