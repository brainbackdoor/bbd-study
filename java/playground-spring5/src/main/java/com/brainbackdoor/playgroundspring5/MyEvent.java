package com.brainbackdoor.playgroundspring5;

import org.springframework.context.ApplicationEvent;
/*
    ApplicationEventPublisher
    이벤트 프로그래밍에 필요한 인터페이스 제공. 옵저버 패턴 구현체
 */
public class MyEvent extends ApplicationEvent {
    private int data;

    public MyEvent(Object source, int data) {
        super(source);
        this.data = data;
    }

    public int getData() {
        return data;
    }
}
