package com.brainbackdoor.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SampleRunner implements ApplicationRunner {

    private Logger logger= LoggerFactory.getLogger(SampleRunner.class);

    @Autowired
    private String hello;

    @Autowired
    private BbdProperties bbdProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("=====================");
        logger.debug("hello");
        logger.debug(bbdProperties.getName());
        logger.debug(bbdProperties.getFullName());
        logger.debug("=====================");

    }
}
