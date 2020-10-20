package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

public class PostDAO implements PostImpl{

	@Override
	public boolean createPost(JsonNode json) {
		boolean flag=false;
		String post_content=json.findPath("post_content").asText();
		String posted_by=json.findPath("post_by").asText();
		String post_id= UUID.randomUUID().toString().replace("-", "");
		Long posted_on=System.currentTimeMillis();
		String insertQry="INSERT INTO post VALUES ("+post_id+", "+posted_by+", "+post_content+","+posted_on+")";
		flag=utils.DBConnectorUtility.CreateData(insertQry);
		return flag;
	}

	@Override
	public List<JsonNode> listPost(String userId) {
		List<JsonNode> l1=new ArrayList<JsonNode>();
		String Qry="SELECT * FROM post WHERE user_id="+userId;	
		ResultSet res=utils.DBConnectorUtility.ReadData(Qry);
		try {
			while(res.next()) {
				String post_content=res.getString("post_content");
				String post_id=res.getString("post_id");
				Long post_on=res.getLong("posted_on");
				ObjectNode obj=Json.newObject();
				obj.set("post_content", Json.toJson(post_content));
				obj.set("post_id", Json.toJson(post_id));
				obj.set("posted_on", Json.toJson(post_on));
				l1.add(Json.toJson(obj));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l1	;
	}

	@Override
	public boolean likePost(JsonNode json) {
		boolean flag=false;
		String post_id=json.findPath("post_id").asText();
		String user_id=json.findPath("user_id").asText();
		Long posted_liked_on=System.currentTimeMillis();
		String insertQry="INSERT INTO posts_liked VALUES ("+post_id+", "+user_id+", "+posted_liked_on+")";
		flag=utils.DBConnectorUtility.CreateData(insertQry);
		return flag;
	}

}
