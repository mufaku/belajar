package com.redis.insert;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

public class insert {
	public static void main(String []args){
		
		
		MongoClient mongo = new MongoClient("localhost",27017);
		DB db = mongo.getDB("imm_topik");
		DBCollection collection = db.getCollection("topics");
		DBCursor docs = collection.find()/* .sort(orderBy) */;
		int a = 1;
		while (docs.hasNext()) 
		{
			DBObject doc = docs.next();
			
			
		}
		
		//Set<String> aw = collection.find();
		/*
			JedisPoolConfig poolConfig = new JedisPoolConfig();
		    poolConfig.setTestOnBorrow(true);*/
	    /*
	    @SuppressWarnings("resource")
	    Jedis jedis = new Jedis("192.168.20.39", 6370);

	    Map<String, String> map = new HashMap<String,String>();*/
	    //System.out.println("==> length " + hits.length);
	    /*
	    int lenDoc = hits.length;
	    int lenData = endDoc - startDoc;
	    int endData = endDoc;
	    
	    if(lenDoc < lenData){
	     endData = startDoc + lenDoc; 
	    }
	    
	    for (int i = startDoc; i < endData; i++) {
	     int docId = hits[i].doc;
	     Document d = search.doc(docId);
	     map.put(String.valueOf(i), d.get("id") + "|" + d.get("content"));
	    }*/
	    
	    //jedis.hmset("xx", map);
		
		}
}
