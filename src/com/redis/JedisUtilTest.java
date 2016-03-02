package com.redis;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class JedisUtilTest {

//    static JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");  
//    static Jedis jedis = pool.getResource(); 
    
    static Jedis jedis = new Jedis("localhost");
    
    public static void main(String[] args) {
//        new RedisPlugin("myRedis", "localhost").start();
//        
//        System.out.println(Redis.use().get("lu"));
        
//        test();
        
        testMap();
    }
    
    public static void testMap(){
        Map<String, String> user = new HashMap<String, String>();
        user.put("name", "wuxianji");
        user.put("age", "28");
        
        jedis.hmset("user", user);
        
        System.out.println(jedis.hmget("user", "name", "age"));
    }
    
    public static void test(){
//        System.out.println(jedis.get("lu"));
        
//        jedis.set("name", "wuxianji");
//        System.out.println(jedis.get("name"));
//        jedis.append("name", "@daisong");
//        System.out.println(jedis.get("name"));
//        jedis.set("name", "good");
//        System.out.println(jedis.get("name"));
//        jedis.del("name");
//        System.out.println(jedis.get("name"));
        
        jedis.mset("name", "wuxianji", "age", "28");
        System.out.println(jedis.mget("name", "age"));
    }
}
