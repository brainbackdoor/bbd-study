package com.brainbackdoor.playgroundspring3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    ApplicationContext ctx;

    @Autowired
    BookRepository bookRepository;

    @Value("${app.name}")
    String appName;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Environment environment = ctx.getEnvironment();
        //-Dapp.name=spring5
        //app.properties : app.name=spring
        System.out.println(environment.getProperty("app.name"));
        System.out.println(appName);
        // output : spring5 즉, vm options이 우선순위가 높다.
    }
}
