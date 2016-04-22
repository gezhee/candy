package com.utobun.candy.framework.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:app-config.xml")
public class RedisTest {
    
    @Resource(name="redisTemplate")
    private RedisTemplate<String, String> redisTemplate;
    
    private Logger log = LoggerFactory.getLogger(RedisTest.class); 
    @Test
    public void test(){
        redisTemplate.opsForList().leftPush("myList","a");
        redisTemplate.opsForList().leftPush("myList","b");
        List<String> listCache = redisTemplate.opsForList().range("myList", 0, 1);
        for(String s:listCache){
            log.info(s);
        }
    }
}
