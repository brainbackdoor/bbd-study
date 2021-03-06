package com.brainbackdoor.playgroundspring9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
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
        SpringApplication app = new SpringApplication(PlaygroundSpring9Application.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
        //Spring을 Web Application을 띄우지 않고,
        //Server mode가 아닌 상태로 띄우는 방식
        //이러면 끄지않아도 자동으로 꺼짐
    }

}
/*
    매번 프록시 클래스를 작성해야 하는가?
    여러 클래스 여러 메소드에 적용하려면?
    So Spring AOP!!
    Spring IoC 컨테이너가 제공하는 기반 시설과 Dynamic Proxy를 사용하여 문제 해결
    - 동적 프록시 : 동적으로 프록시 객체를 생성하는 방법
        - 자바가 제공하는 방법은 인터페이스 기반 프록시 생성
        - CGlib은 클래스 기반 프록시도 지원
    - Spring IoC : 기존 빈을 대체하는 동적 프록시 빈을 만들어 등록시켜준다.
        - AbstractAutoProxyCreator implements BeanPostProcessor
 */

