package com.brainbackdoor.playgroundspring3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Single {

    @Autowired
    Proto proto; // proxy bean이 주입

    public Proto getProto() {
        return proto;
    }
}
