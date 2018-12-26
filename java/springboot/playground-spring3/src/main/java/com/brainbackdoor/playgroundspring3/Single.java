package com.brainbackdoor.playgroundspring3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Single {

    @Autowired
    Proto proto;

    /*
    profile : Test, Production 등 각각의 환경에서 쓰일 Bean들의 묶음
              ApplicationContext의 EnvironmentCapable을 통해 사용가능

     */
    public Proto getProto() {
        return proto;
    }
}
