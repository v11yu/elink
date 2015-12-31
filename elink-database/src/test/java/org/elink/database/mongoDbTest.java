package org.elink.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elink.database.mongodb.MongoRootConfiguration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MongoRootConfiguration.class})
public class mongoDbTest {
	@Autowired
    private MongoTemplate mongoTemplate;
	protected DBCollection collection;
	void insert(int id,BasicDBObject updateObj){
		collection = mongoTemplate.getCollection("entitySource");
		System.out.println(collection.findOne());
		DBObject query = new BasicDBObject("_id", id);
		collection.update(query, new BasicDBObject("$set", updateObj));
	}
	@Test
	public void testInsertDBObjects() throws JSONException{
		List<DBObject> ls = new ArrayList<DBObject>();
		BasicDBObject updateObj = new BasicDBObject();
		DBObject obj = new BasicDBObject();
		obj.put("haha", "hehe");
		obj.put("v22", 055);
		obj.put("v34", 0.55);
		ls.add(obj);
		DBObject obj2 = new BasicDBObject();
		obj2.put("name","tom");
		ls.add(obj2);
		updateObj.append("extraJson",ls);
		System.out.println(ls.toString());
		insert(440,updateObj);
	}
	@Test
	public void testInsertStrings(){
		List<String> ls = new ArrayList<String>();
		ls.add("hello");
		ls.add("sad");
		ls.add("happy");
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.append("extraStrings",ls);
		System.out.println(ls.toString());
		insert(440,updateObj);
		
	}
}
