package com.ph.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoPh {

	public static void main(String[] args) {
		// open connection;
		MongoClient mongo = new MongoClient("192.168.20.25", 25017);
		//DB db = mongo.getDB("imm_topik");
		//DBCollection collection = db.getCollection("topics");
		DBCollection coll = UseDB(mongo,"imm_topic","topics");
		FindTextAndNot(coll, "jokowi", "mundur", "","","","");
		//SampleLimitSort(collection,-1, 10 , "id", "topic", "keywords");
		//Indexingfield(collection, "id", 1);
	}
	
	public static DBCollection UseDB(MongoClient mongo,String NamaDatabase,String NamaCollections) {
		DB db = mongo.getDB(NamaDatabase);
		DBCollection collection = db.getCollection(NamaCollections);
		return collection;
	}
	
	
	public static void SampleInsert(DBCollection collection,String _id,String isi) {
		BasicDBObject query = new BasicDBObject(_id,isi);
		collection.insert(query);
	}
	
	public static void Insert(DBCollection collection,BasicDBObject IsiSendiri) {
		BasicDBObject query = new BasicDBObject(IsiSendiri);
		collection.insert(query);
	}
	
	public static void DeleteWhere(DBCollection collection,String field,String value){
		BasicDBObject query = new BasicDBObject(field,value);
		collection.remove(query);
	}
	
	public static void DeleteAll(DBCollection collection){
		collection.drop();
	}
	
	public static void SampleGetById(DBCollection collection,String value,String field1,String field2,String field3){
		BasicDBObject query = new BasicDBObject("_id",value);
		DBCursor docs = collection.find(query);
		while (docs.hasNext()) 
		{
			DBObject doc = docs.next();
			System.out.println(field1 + " : "+ doc.get(field1)+ "\n" +field2 + " : "+field2 +
			"\n" + field3+" : "+ doc.get(field3)+"\n\n "
			+ "===============================================================\n\n");
		}
	}
	
	public static void SampleLimitSort(DBCollection collection,int sortir,int lim,String field1,String field2,String field3){
		BasicDBObject sr = new BasicDBObject("_id",sortir);
		DBCursor docs = collection.find().limit(lim).sort(sr);
		while (docs.hasNext()) 
		{
			DBObject doc = docs.next();
			System.out.println(field1 + " : "+ doc.get(field1)+ "\n" +field2 + " : "+field2 +
			"\n" + field3+" : "+ doc.get(field3)+"\n\n "
			+ "===============================================================\n\n");
		}
	}
	
	public static void IndexingText(DBCollection collection,String field,String type){
		BasicDBObject Fulltextindex = new BasicDBObject(field,type);
		collection.createIndex(Fulltextindex);
	}
	
	public static void Indexingfield(DBCollection collection,String field,int tipe){
		BasicDBObject indek = new BasicDBObject(field,tipe);
		collection.createIndex(indek);
	}
	
	public static void SampleGetAll(DBCollection collection,String field1,String field2,String field3){
		DBCursor docs = collection.find();
		while (docs.hasNext()) 
		{
			DBObject doc = docs.next();
			System.out.println(field1 + " : "+ doc.get(field1)+ "\n" +field2 + " : "+field2 +
			"\n" + field3+" : "+ doc.get(field3)+"\n\n "
			+ "===============================================================\n");
		}
	}
	
	public static void FindText(DBCollection collection,String txt){
		BasicDBObject search = new BasicDBObject("$search",txt );
		BasicDBObject teks = new BasicDBObject("$text",search);
		DBCursor docs = collection.find(teks);
		while (docs.hasNext()) 
		{
			DBObject doc = docs.next();
			System.out.println(  "id : "+ doc.get("_id")+ "\nTopik "  +doc.get("topic") +
			"\nkeywords : "+ doc.get("keywords")+"\n\n "
			+ "===============================================================\n");
		}
	}
	
	public static void FindTextOrNot(DBCollection collection,String txt,String txt2,String txt3,String not,String not2,String not3){
		BasicDBObject search = new BasicDBObject("$search",txt+" "+txt2+" "+txt3+" -"+not+" -"+not2+" -"+not3 );
		BasicDBObject teks = new BasicDBObject("$text",search);
		DBCursor docs = collection.find(teks);
		while (docs.hasNext()) 
		{
			DBObject doc = docs.next();
			System.out.println(  "id : "+ doc.get("_id")+ "\nTopik "  +doc.get("topic") +
			"\nkeywords : "+ doc.get("keywords")+"\n\n "
			+ "===============================================================\n");
		}
	}

	public static void FindTextAndNot(DBCollection collection,String txt,String txt2,String txt3,String not,String not2,String not3){
		BasicDBObject search = new BasicDBObject("$search"," \""+txt+"\""+"\""+txt2+"\""+"\""+txt3+"\" -"+not+" -"+not2+" -"+not3 );
		BasicDBObject teks = new BasicDBObject("$text",search);
		DBCursor docs = collection.find(teks);
		while (docs.hasNext()) 
		{
			DBObject doc = docs.next();
			System.out.println(  "id : "+ doc.get("_id")+ "\nTopik "  +doc.get("topic") +
			"\nkeywords : "+ doc.get("keywords")+"\n\n "
			+ "===============================================================\n");
		}
	}
	
	public static void FindEver(DBCollection collection,BasicDBObject IsiSendiri){
		BasicDBObject search = new BasicDBObject("$search",IsiSendiri );
		BasicDBObject teks = new BasicDBObject("$text",search);
		DBCursor docs = collection.find(teks);
		while (docs.hasNext()) 
		{
			DBObject doc = docs.next();
			System.out.println(  "id : "+ doc.get("_id")+ "\nTopik "  +doc.get("topic") +
			"\nkeywords : "+ doc.get("keywords")+"\n\n "
			+ "===============================================================\n");
		}
	}
	
}