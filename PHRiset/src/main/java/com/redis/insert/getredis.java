package com.redis.insert;

import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

public class getredis {
	public static void main(String [] args){
	
	Jedis jedis = new Jedis("localhost");  
	String username = "12";
	Map<String, String> properties = jedis.hgetAll("user:" + username);
	


    Map<String, String> data;
    while(true){
    data = jedis.hgetAll("id");
    }    
  }
}