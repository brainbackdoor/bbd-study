package com.brainbackdoor.playgroundspring3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Single {

    @Autowired
    Proto proto; // proxy bean이 주입

    int value = 0;
    // singleton에서는 Property가 공유되므로 Thread-safe하지 않다.
    // 그리고 Singleton은 ApplicationContext 초기 구동시 인스턴스를 생성한다.

    public Proto getProto() {
        return proto;
    }
}
