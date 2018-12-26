package com.brainbackdoor.playgroundspring10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    EventService eventService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        eventService.createEvent(null); // 여기서 error가 남
    }
}
