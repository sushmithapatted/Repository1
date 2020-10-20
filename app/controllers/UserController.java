package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;

import models.userImpl;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.ConstructResponseUtil;
import utils.ResponseMessage;

public class UserController extends Controller{
	
	userImpl userImpl;
	
	@Inject
	public UserController(userImpl userImpl) {
		this.userImpl=userImpl;
	}

	public Result addUser() {
		boolean flag=true;
		JsonNode json = request().body().asJson();
		try {		
			flag=userImpl.addUser(json);
		} catch (Exception e) {
			return badRequest(ConstructResponseUtil.createFailureResponsev1(null, e.getMessage(), false));
		}
		if(flag) {
		return ok(ConstructResponseUtil.createSuccessResponsev1(null,ResponseMessage.USER_ADD_SUCCESS, true));
		}
		else {
			return badRequest(ConstructResponseUtil.createFailureResponsev1(null,ResponseMessage.USER_ALREADY_EXISTS, true));
			
		}
	}
	
	public Result getUser(String search) {
		List<JsonNode> L1=new ArrayList<JsonNode>();
		try {
			L1=userImpl.getUser(search);
		} catch (Exception e) {
			return badRequest(ConstructResponseUtil.createFailureResponsev1(null, e.getMessage(), false));
		}
		
		return ok(ConstructResponseUtil.createSuccessResponsev1(Json.toJson(L1),ResponseMessage.USER_FETCH_SUCCESS, true));
		
	}
	public Result followUser() {
		boolean flag=true;
		JsonNode json = request().body().asJson();
		try {		
			flag=userImpl.followUser(json);
		} catch (Exception e) {
			return badRequest(ConstructResponseUtil.createFailureResponsev1(null, e.getMessage(), false));
		}
		if(flag) {
		return ok(ConstructResponseUtil.createSuccessResponsev1(null,ResponseMessage.DATA_UPDATED, true));
		}
		else {
			return badRequest(ConstructResponseUtil.createFailureResponsev1(null,ResponseMessage.FAILED_TO_UPDATE, true));
			
		}
	}
}
