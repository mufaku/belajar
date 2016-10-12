package com.ph.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class RedisPh {

	public static void main(String[] args) {
		// open connection;
		Jedis jedis = new Jedis("localhost");
		
		//panggil Fungsi
		//Update(jedis, "list", 0, "gantiiiiiiii");
		//SampleInsert(jedis);
		//SampleGetById(jedis, "list", 1);
		//DeleteAll(jedis, "list");
		//InsertList(jedis, "list", "sdfsdfsdfsdfs");
		
		SampleGetAll(jedis, "list", 0, -1);
		//Count_row(jedis, "list");
	}

	public static void SampleInsert(Jedis jedis,String key,String value) {
		jedis.set(key,value);
	}
	
	public static void InsertList(Jedis jedis,String key,String value) {
		jedis.rpush(key, value);
	}
	
	public static void SampleDelete(Jedis jedis,String field,int indeks,int indeksall){
		jedis.ltrim(field, indeks, indeksall);
		System.out.println("Index "+indeksall+" dst berhasil dihapus");
	}
	
	public static void DeleteOne(Jedis jedis,String field,int indeks){
		jedis.lset(field, indeks, "DELETE");
		jedis.lrem(field, indeks, "DELETE");
		System.out.println("Index "+indeks+" dst berhasil dihapus");
	}

	public static void Update(Jedis jedis,String field,int indeks,String isi){
		jedis.lset(field, indeks,isi );
		String hasil = jedis.lindex(field, indeks);
		System.out.println(hasil);
	}
	
	public static void DeleteAll(Jedis jedis,String field){
		jedis.del(field);
		System.out.println("List telah terhapus");
	}
	
	public static void SampleGetById(Jedis jedis,String NamaList,long index){
		String a =jedis.lindex(NamaList, index);
		System.out.println(a);
	}
	
	public static void Count_row(Jedis jedis,String NamaList){
		Long a =jedis.llen(NamaList);
		System.out.println(a);
	}
	
	public static void SampleGetAll(Jedis jedis,String list){
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		List<String> lrange = jedis.lrange(list,0,-1);
		
		int a=1;
		for (String string : lrange) 
		{
			System.out.print("Index ke"+a+" : ");
			System.out.println(string);
			a++;
		}
	}
	
	public static void SampleGetIndex(Jedis jedis,String list,int IndexAwal,int IndexAkhir){
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		List<String> lrange = jedis.lrange(list,IndexAwal,IndexAkhir);
		
		int a=1;
		for (String string : lrange) 
		{
			System.out.println(string);
			a++;
		}
	}
	
}
