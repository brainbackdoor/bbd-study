package com.educhoice.motherchoice.utils;

import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class SseEmitterManager {

    private LoadingCache<BasicAccount, SseEmitter> emitterStorage;

    public SseEmitterManager() {
        super();
        this.emitterStorage = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.DAYS).build(new CacheLoader<BasicAccount, SseEmitter>() {
            @Override
            public SseEmitter load(BasicAccount account) throws Exception {
                return new SseEmitter();
            }
        });
    }

    public SseEmitter getEmitter(BasicAccount account) {
        return emitterStorage.getUnchecked(account);
    }

    public void sendEvent(BasicAccount account, ApplicationEvent event) throws IOException{
        emitterStorage.getUnchecked(account).send(event);
    }
}
