package com.example.demo;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


import java.util.ArrayList;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTest {

    private static final Logger log = LoggerFactory.getLogger(RedisConfigTest.class);

    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    private final static String KEY_NAME = "users:info";

    @Before
    public void init() {
        redisTemplate.delete(this.KEY_NAME);
    }

    @Test
    public void test() {
        String key = "key:springboot";
        redisTemplate.opsForValue().set(key, "Hello");
        String value = (String) redisTemplate.opsForValue().get(key);
        Assert.assertEquals("Hello", value);
    }

    @Test
    public void testListOperationString() {
        ListOperations<String, String> listOps = redisTemplate.opsForList();

        listOps.leftPush(KEY_NAME, "bbd");
        listOps.leftPush(KEY_NAME, "pobi");
        listOps.leftPush(KEY_NAME, "toby");

        List<String> users = listOps.range(KEY_NAME, 0, -1);
        Assert.assertEquals(3, users.size());
        users.stream().forEach(u -> log.debug("{}", u));
    }

    @Test
    public void testListOperationObject() {
        ListOperations<String, User> listOperations = redisTemplate.opsForList();
        User user1 = new User("bbd", "M");
        listOperations.rightPush(KEY_NAME, user1);
        User user2 = new User("lemon", "F");
        listOperations.rightPush(KEY_NAME, user2);

        List<User> users = listOperations.range(KEY_NAME, 0, -1);
        users.stream().forEach(u -> log.debug("{}", u));
    }


    @Test
    public void testListQueue() {
        ListOperations<String, User> listOperations = redisTemplate.opsForList();

        User user1 = new User("bbd", "M");
        listOperations.rightPush(KEY_NAME, user1);
        User user2 = new User("lemon", "F");
        listOperations.rightPush(KEY_NAME, user2);

        User popUser1 = listOperations.leftPop(KEY_NAME);
        Assert.assertEquals("bbd", popUser1.getName());

        Assert.assertEquals(1, listOperations.size(KEY_NAME).intValue());
    }

    @Test
    public void testSearchListObject() {
        ListOperations<String, User> listOperations = redisTemplate.opsForList();
        User user1 = new User("bbd", "M");
        listOperations.rightPush(KEY_NAME, user1);
        User user2 = new User("lemon", "F");
        listOperations.rightPush(KEY_NAME, user2);

        List<User> users = listOperations.range(KEY_NAME, 0, -1);
        users.stream().forEach(u -> log.debug("{}", u));

        User target1 = new User("bbd", "M");
        User target2 = new User("bbd", "F");
        Assert.assertEquals(true, users.contains(target1));
        Assert.assertEquals(false, users.contains(target2));
    }

    @Test
    public void testListPushObject() {
        ListOperations<String, User> listOperations = redisTemplate.opsForList();
        User user1 = new User("bbd", "M");
        User user2 = new User("lemon", "F");
        listOperations.rightPushAll(KEY_NAME, user1, user2);

        List<User> users = listOperations.range(KEY_NAME, 0, -1);
        users.stream().forEach(u -> log.debug("{}", u));
    }

}

