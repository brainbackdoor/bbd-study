package com.educhoice.motherchoice.utils.exceptions.resolvers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SentryExceptionResolverTest {

    @Test(expected = RuntimeException.class)
    public void 예외처리테스트() {
        throw new RuntimeException("test exception");
    }

}