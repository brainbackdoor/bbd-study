package com.educhoice.motherchoice.utils;

import com.educhoice.motherchoice.utils.exceptions.domain.InternalProcessingException;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PersistenceUtils {

    public <T> T unproxyObject(T proxy) {
        //TODO implement object-unproxying logic.
        Hibernate.initialize(proxy);
        return proxy;
    }

    public <T> T unproxyChildEntities(T aggregateRoot){
        //TODO implement object-childitem unproxying logic.
        Arrays.stream(aggregateRoot.getClass().getFields()).filter(f -> f.getClass().isAssignableFrom(List.class))
                .peek(f -> f.setAccessible(true))
                .map(f -> {
                    try {
                        return f.get(aggregateRoot);
                    } catch (IllegalAccessException e) {
                        throw new InternalProcessingException(e, e.getMessage());
                    }
                })
                .forEach(o -> Hibernate.initialize(o));

        return aggregateRoot;
    }
}
