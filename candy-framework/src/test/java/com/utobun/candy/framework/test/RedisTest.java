package com.utobun.candy.framework.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:app-config.xml")
@Transactional
public class RedisTest {
    
    @Resource(name="redisTemplate")
    private RedisTemplate<String, String> redisTemplate;
    
    private Logger log = LoggerFactory.getLogger(RedisTest.class); 
    @Test
    public void test(){
        log.info("test");
        log.debug("debug");
        log.error("error");
        System.out.println("teststst");
    }
}
