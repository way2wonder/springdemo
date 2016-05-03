package com.feifan.jredis;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil
{
    private Logger logger = Logger.getLogger(this.getClass().getName());
 
    private JedisUtil(){}
 
    private static class RedisUtilHolder{
        private static final JedisUtil instance = new JedisUtil();
    }
 
    public static JedisUtil getInstance(){
        return RedisUtilHolder.instance;
    }
 
    private static Map<String,JedisPool> maps = new HashMap<String,JedisPool>();
 
    private static JedisPool getPool(String ip, int port){
        String key = ip+":"+port;
        JedisPool pool = null;
        if(!maps.containsKey(key))
        {
            JedisPoolConfig config = new JedisPoolConfig();
//            config.setMaxActive(RedisConfig.MAX_ACTIVE);
            config.setMaxIdle(RedisConfig.MAX_IDLE);
//            config.setMaxWait(RedisConfig.MAX_WAIT);
            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);
 
            pool = new JedisPool(config,ip,port,RedisConfig.TIMEOUT);
            maps.put(key, pool);
        }
        else
        {
            pool = maps.get(key);
        }
        return pool;       
    }
 
    public Jedis getJedis(String ip, int port)
    {
        Jedis jedis = null;
        int count = 0;
        do
        {
            try
            {
                jedis = getPool(ip,port).getResource();
            }
            catch (Exception e)
            {
                logger.log(null, "get redis master1 failed!",e);
                closeJedis(ip,port);
            }
        }
        while(jedis == null && count<RedisConfig.RETRY_NUM);
        return jedis;
    }
 
    
    public void closeJedis(String ip, int port){
          getPool(ip,port).close();
    }
}
 





