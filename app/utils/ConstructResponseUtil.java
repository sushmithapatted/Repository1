package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.libs.Json;

public class ConstructResponseUtil {

	public static ObjectNode createFailureResponsev1(Object response, String message, boolean ok) {
		ObjectNode result = Json.newObject();
		if (ok) {
			result.put("status", 400);
			result.put("message", message);
			if (response instanceof String)
				result.put("data", (String) response);
			else {
				ObjectNode data = Json.newObject();
				data.set("list", (JsonNode) response);
				result.set("data", data);
			}
		} else {
			result.put("status", 400);
			result.put("message", message);
		}

		return result;
	}
	
	public static ObjectNode createSuccessResponsev1(Object response, String message, boolean ok) {
		ObjectNode result = Json.newObject();
		if (ok) {
			result.put("status", 200);
			result.put("message", message);
			result.put("system_time", System.currentTimeMillis());
			if (response instanceof String)
				result.put("data", (String) response);
			else {
				result.set("data", (JsonNode) response);
			}
		} else {
			result.put("status", 200);
			result.put("message", message);
			result.put("system_time", System.currentTimeMillis());
		}

		return result;
	}
}
