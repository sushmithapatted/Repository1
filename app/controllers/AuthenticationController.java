package controllers;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;

import models.SettingsImpl;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.ConstructResponseUtil;
import utils.ResponseMessage;

public class AuthenticationController extends Controller{

private final SettingsImpl settingsImpl;
	
	@Inject
	public AuthenticationController(SettingsImpl settingsImpl) {
		this.settingsImpl = settingsImpl;
		
	}
	
	public Result login() {
		JsonNode json = request().body().asJson();
	
		if (json == null) {
			return badRequest(ConstructResponseUtil.createFailureResponsev1(null,
					ResponseMessage.LOGIN_FAILURE, false));
		}
		JsonNode loginObject = null;
		try {
			loginObject = settingsImpl.userLogin(json); 
			
		} catch (Exception e) {
			
			return badRequest(ConstructResponseUtil.createFailureResponsev1(null, e.getMessage(), false));
		}
		if(loginObject.get("token")!=null) {
		return ok(ConstructResponseUtil.createSuccessResponsev1(Json.toJson(loginObject),
				ResponseMessage.LOGIN_SUCCESS, true));
		}else {
			return badRequest(ConstructResponseUtil.createFailureResponsev1(null,
					ResponseMessage.LOGIN_FAILURE, false));
		}
	}
	
	public Result logout() {
		JsonNode json = request().body().asJson();
	
		if (json == null) {
			return badRequest(ConstructResponseUtil.createFailureResponsev1(null,
					ResponseMessage.LOGOUT_FAILURE, false));
		}
		boolean logout = false;
		try {
			logout = settingsImpl.userlogout(json); 
			
		} catch (Exception e) {
			
			return badRequest(ConstructResponseUtil.createFailureResponsev1(null, e.getMessage(), false));
		}
		if(logout) {
			return ok(ConstructResponseUtil.createSuccessResponsev1(null,
					ResponseMessage.LOGOUT_SUCCESS, true));
		}else {
			return badRequest(ConstructResponseUtil.createFailureResponsev1(null,
					ResponseMessage.LOGOUT_FAILURE, false));
		}
		
	}
}
