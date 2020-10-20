package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.libs.Json;

public class UserDAO implements userImpl{

	@Override
	public boolean addUser(JsonNode json) {
		boolean flag=false;
		String user_name=json.findPath("user_name").asText();
		String email=json.findPath("email").asText();
		String password=json.findPath("password").asText();
		String user_id= UUID.randomUUID().toString().replace("-", "");
		Long created_on=System.currentTimeMillis();
		String insertQry="INSERT INTO user VALUES ("+user_name+", "+email+", "+password+","+user_id+","+created_on+")";
		flag=utils.DBConnectorUtility.CreateData(insertQry);
		return flag;
	}

	@Override
	public List<JsonNode> getUser(String search) {
		List<JsonNode> l1=new ArrayList<JsonNode>();
		String Qry="SELECT * FROM user WHERE user_name="+search;	
		ResultSet res=utils.DBConnectorUtility.ReadData(Qry);
		try {
			while(res.next()) {
				String user_name=res.getString("user_name");
				String user_id=res.getString("user_id");
				String email=res.getString("email");
				ObjectNode obj=Json.newObject();
				obj.set("user_name", Json.toJson(user_name));
				obj.set("user_id", Json.toJson(user_id));
				obj.set("email", Json.toJson(email));
				l1.add(Json.toJson(obj));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l1	;	}

	@Override
	public boolean followUser(JsonNode json) {
		boolean flag=false;
		String follows_user_id=json.findPath("follows_user_id").asText();
		String follower_id=json.findPath("follower_id").asText();
		String id= UUID.randomUUID().toString().replace("-", "");
		Long created_on=System.currentTimeMillis();
		String insertQry="INSERT INTO followers VALUES ("+follows_user_id+", "+follower_id+","+id+","+created_on+")";
		flag=utils.DBConnectorUtility.CreateData(insertQry);
		return flag;
	}

}
