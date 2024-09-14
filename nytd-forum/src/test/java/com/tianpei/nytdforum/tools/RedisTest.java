package com.tianpei.nytdforum.tools;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void testSet(){
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("username","zhangsan");
    }
}
