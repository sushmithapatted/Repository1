package models;

import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.libs.Json;

public class SettingsDAO implements SettingsImpl{

	@Override
	public JsonNode userLogin(JsonNode json) {
		ObjectNode obj=Json.newObject();
		boolean flag=false;
		
		String email=json.findPath("email").asText();
		String token= UUID.randomUUID().toString().replace("-", "");
		Long created_on=System.currentTimeMillis();
		String insertQry="INSERT INTO session VALUES ("+email+", "+token+","+created_on+")";
		flag=utils.DBConnectorUtility.CreateData(insertQry);
		if(flag) {
			obj.set("email", Json.toJson("email"));
			obj.set("token", Json.toJson("token"));
		}
		return Json.toJson(obj);
	}

	@Override
	public boolean userlogout(JsonNode json) {
		boolean flag=false;
		ObjectNode obj=Json.newObject();
		String user_id=json.findPath("user_id").asText();
		String token=json.findPath("token").asText();
		String delete="DELETE FROM session WHERE token="+token;
		return flag;
	}

}
