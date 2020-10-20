package models;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.ImplementedBy;

@ImplementedBy(PostDAO.class)
public interface PostImpl {

	boolean createPost(JsonNode json);

	List<JsonNode> listPost(String userId);

	boolean likePost(JsonNode json);

}
