package com.systelab.redis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
        jedis.set("patient:name", "Peter");
        String cachedResponse = jedis.get("patient:name");
        assertEquals("Peter", cachedResponse);
    }

    @Test
    public void testNumbers() {
        Long seq=jedis.incr("sequence");
        assertEquals(1l,seq.longValue());

        seq=jedis.incr("sequence");
        assertEquals(2l,seq.longValue());

        jedis.del("sequence");

        seq=jedis.incr("sequence");
        assertEquals(1l,seq.longValue());
    }

    @Test
    public void testLists() {
        jedis.del("tasks");
        jedis.lpush("tasks", "first");
        jedis.lpush("tasks", "second");

        String task = jedis.rpop("tasks");
        assertEquals("first", task);

        task = jedis.rpop("tasks");
        assertEquals("second", task);
    }

    @Test
    public void testSets() {
        jedis.del("tasks");
        jedis.sadd("tasks", "first");
        jedis.sadd("tasks", "second");
        jedis.sadd("tasks", "third");

        Set<String> tasks = jedis.smembers("tasks");
        assertTrue(jedis.sismember("tasks", "second"));
    }

    @Test
    public void testHashes() {
        jedis.del("patient#1");

        jedis.hset("patient#1", "name", "Peter");
        jedis.hset("patient#1", "surname", "Avila");

        String name = jedis.hget("patient#1", "name");

        Map<String, String> fields = jedis.hgetAll("patient#1");
        String surname = fields.get("surname");

        assertEquals("Peter", name);
        assertEquals("Avila", surname);
    }

    @Test
    public void testSortedSets() {

    }

    @AfterEach
    private void tearDown() {
    }

    @AfterAll
    private static void tearDownAll() {
    }
}