package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.ImplementedBy;

@ImplementedBy(SettingsDAO.class)
public interface SettingsImpl {

	JsonNode userLogin(JsonNode json);

	boolean userlogout(JsonNode json);

}
