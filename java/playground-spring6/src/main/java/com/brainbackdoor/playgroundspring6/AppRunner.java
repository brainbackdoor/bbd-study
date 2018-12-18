package com.brainbackdoor.playgroundspring6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;


@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileSystemXmlApplicationContext ctx =
                //app.xml => resource
//                new ClassPathXmlApplicationContext("app,xml");
                //Filesystem 경로를 기준으로 app.xml을 찾게됨
                new FileSystemXmlApplicationContext("app,xml");
        Resource resource = resourceLoader.getResource("classpath:test.txt");
        System.out.println(resource.exists());
        System.out.println(resource.getDescription());
    }
}
