package com.brainbackdoor.playgroundspring5;

/*
    비침투성, Transparent.. => 내 코드에 스프링 코드가 노출되지 않는 것
    이것이 POJO기반의 프로그래밍
 */
public class MyEvent {
    private int data;
    private Object source;

    public MyEvent(Object source, int data) {
        this.source = source;
        this.data = data;
    }

    public Object getSource() {
        return source;
    }

    public int getData() {
        return data;
    }
}
