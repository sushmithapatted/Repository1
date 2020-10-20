package models;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.ImplementedBy;

@ImplementedBy(UserDAO.class)
public interface userImpl {

	boolean addUser(JsonNode json);

	List<JsonNode> getUser(String search);

	boolean followUser(JsonNode json);

}
