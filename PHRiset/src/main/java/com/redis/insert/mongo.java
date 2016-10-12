package com.redis.insert;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

public class mongo {
	public static void main(String[] args) throws UnknownHostException, JsonProcessingException {
		Jedis jedis = new Jedis("localhost");
		
		MongoClient mongo = new MongoClient("192.168.20.25", 25017);
		DB db = mongo.getDB("imm_topik");
		DBCollection collection = db.getCollection("topics");
		
		int jumlah = (int) collection.count();
		ObjectMapper mapper = new ObjectMapper();
		DBCursor docs = collection.find()/* .sort(orderBy) */;
		int a = 1;
		//List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		//List<String> lrange = jedis.lrange("list", 0, -1);

		//for (String string : lrange) {
			//System.out.println(string);
		//}
		//------------Hitung Data(Count)-------------------------
		//System.out.println("TOTAL DATA : " + lrange.size());
		
		
		while (docs.hasNext()) 
		{
			DBObject doc = docs.next();
			//String kode = (String) doc.get("id");
			//long kodelong = Long.parseLong(kode);
			Map<String, String> map = new HashMap<String,String>();
			map.put("id",(String) doc.get("id") );
			map.put("keywords",(String) doc.get("keywords"));
			
			//insert map ke redis
			//jedis.hmset("id:" + (String) doc.get("id"),map);
			//listMap.add(map);
			//===================================
			
			//Converter map ke json 
			DBObject db1 = (DBObject) JSON.parse(mapper.writeValueAsString(map));
			jedis.rpush("list", db1.toString());
			System.out.println(db1.toString());
			//========================================
				
			//bisa buat update
			/*jedis.lset("list",a,(String) doc.get("keywords"));
			a++;*/
			
			// {id: id, keyword:keyword}
			
			//membuat list dengan key sama
			//jedis.rpush("list3", db1.toString());
			//cara memanggil menggunakan Lrange [nama list] [baris ke] /hingga/ [baris ke] /pada cli
			//============================
			
			/*--------------Belajar----------------------*/
			//jedis.lset("list1", Long.parseLong(doc.get("id").toString()) , doc.get("keywords").toString() );
			//jedis.set((String) doc.get("id"),(String) doc.get("topic"));
			//jedis.hmset((String) doc.get("id"), map);
		
		}
		//jedis.lset("list2",0,"");
		//System.out.println(listMap.toString());
	}
}