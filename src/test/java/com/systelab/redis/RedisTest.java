package com.systelab.redis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

class RedisTests {

    static Jedis jedis;

    @BeforeAll
    private static void initAll() {
        jedis = new Jedis("127.0.0.1", 6379);
    }

    @BeforeEach
    private void init() {
    }

    @Test
    public void testStrings() {
        jedis.set("events/city/rome", "32,15,223,828");
        String cachedResponse = jedis.get("events/city/rome");
        assertEquals("32,15,223,828",cachedResponse);
    }

    @Test
    public void testLists() {
        jedis.del("queue#tasks");
        jedis.lpush("queue#tasks", "firstTask");
        jedis.lpush("queue#tasks", "secondTask");

        String task = jedis.rpop("queue#tasks");
        assertEquals("firstTask",task);

        task = jedis.rpop("queue#tasks");
        assertEquals("secondTask",task);
    }

    @AfterEach
    private void tearDown() {
    }

    @AfterAll
    private static void tearDownAll() {
    }

}