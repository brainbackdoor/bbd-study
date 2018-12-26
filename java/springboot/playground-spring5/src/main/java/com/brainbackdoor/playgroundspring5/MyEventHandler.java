package com.brainbackdoor.playgroundspring5;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
/*
    ApplicationListner<이벤트> 구현한 클래스 만들어서 빈으로 등록하기
    스프링 4.2부터는 @EventListener를 사용해서 빈의 메소드에 사용할 수 있다.
    기본적으로는 synchornized
    순서를 정하고 싶다면 @Order와 함께 사용
    비동기적으로 실행하고 싶다면 @Async와 함꼐 사용
 */
/*
    ContextRefreshedEvent: ApplicationContext를 초기화 했거나 리프래시했을때 발생
    ContextStartedEvent: ApplicationContext를 start()하여 라이프사티클 빈들이 시작신호를 받은 시점
    ContextStoppedEvent: stop()...
    ContextClosedEvent: close()하여 싱글톤 빈 소멸되는 시점에 발생
    RequestHandledEvent: HTTP 요청을 처리했을 떄 발생
 */
@Component
public class MyEventHandler {

    @EventListener
    @Async
    public void handle(MyEvent myEvent) {
        System.out.println(Thread.currentThread().toString());
        System.out.println("이벤트를 받았다. 데이터는 " + myEvent.getData());
    }

    @EventListener
    @Async
    public void handle(ContextRefreshedEvent myEvent) {
        System.out.println(Thread.currentThread().toString());
        System.out.println("ContextRefreshedEvent");
    }
    @EventListener
    @Async
    public void handle(ContextClosedEvent myEvent) {
        System.out.println(Thread.currentThread().toString());
        System.out.println("ContextClosedEvent");
    }
}
