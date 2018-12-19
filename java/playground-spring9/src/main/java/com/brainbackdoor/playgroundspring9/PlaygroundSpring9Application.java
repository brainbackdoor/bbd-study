package com.brainbackdoor.playgroundspring9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
    Spring AOP
        프록시기반의 AOP 구현체
        스프링 빈에만 AOP를 적용할 수 있다.
        모든 AOP 기능을 제공하는 것이 목적이 아니라,
        스프링 IoC와 연동하여 엔터프라이즈 애플리케이션에서 가장 흔한
        문제에 대한 해결책을 제공하는 것이 목적

    프록시패턴 : 접근제어 또는 부가기능을 추가하는 목적으로 쓰임
        Client -> <<interface>> Subject <- Proxy -> Real Subject
 */
@SpringBootApplication
public class PlaygroundSpring9Application {

    public static void main(String[] args) {
        SpringApplication.run(PlaygroundSpring9Application.class, args);
    }

}

