package com.feifan.jredis;
// 其中JedisUtil是对jedis做的简单封装，代码如下：


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import redis.clients.jedis.Jedis;


public class JRedisTest
{
    private static final String ipAddr = "127.0.0.1";

    private static final int port = 6379;

    private static Jedis jedis = null;

    @BeforeClass
    public static void init()
    {
        jedis = JedisUtil.getInstance().getJedis(ipAddr, port);
    }

    @AfterClass
    public static void close()
    {
        JedisUtil.getInstance().closeJedis(ipAddr, port);
    }

    @Test
    public void testNumber()
    {
        jedis.flushDB();
        jedis.set("key1", "1");
        jedis.set("key2", "2");
        jedis.set("key3", "2.3");
        System.out.println("key1的值：" + jedis.get("key1"));
        System.out.println("key2的值：" + jedis.get("key2"));
        System.out.println("key1的值加1：" + jedis.incr("key1"));
        System.out.println("获取key1的值：" + jedis.get("key1"));
        System.out.println("key2的值减1：" + jedis.decr("key2"));
        System.out.println("获取key2的值：" + jedis.get("key2"));
        System.out.println("将key1的值加上整数5：" + jedis.incrBy("key1", 5));
        System.out.println("获取key1的值：" + jedis.get("key1"));
        System.out.println("将key2的值减去整数5：" + jedis.decrBy("key2", 5));
        System.out.println("获取key2的值：" + jedis.get("key2"));
        System.out.println("11111111111");
        System.out.println("获取key2的值加浮点值：" + jedis.incrByFloat("key2",1.1));
    }
}
