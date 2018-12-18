package com.brainbackdoor.playgroundspring5;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventHandler implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        System.out.println("이벤트를 받았다. 데이터는 " + myEvent.getData());
    }
}
