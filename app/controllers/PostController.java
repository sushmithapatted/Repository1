package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;

import models.PostImpl;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.ConstructResponseUtil;
import utils.ResponseMessage;

public class PostController extends Controller{
	PostImpl postImpl;
	
	@Inject
	public PostController(PostImpl postImpl) {
		this.postImpl=postImpl;
	}

	public Result createPost() {
		boolean flag=true;
		
		JsonNode json = request().body().asJson();
		try {		
			flag=postImpl.createPost(json);
		} catch (Exception e) {
			return badRequest(ConstructResponseUtil.createFailureResponsev1(null, e.getMessage(), false));
		}
		
		return ok(ConstructResponseUtil.createSuccessResponsev1(null,ResponseMessage.POST_ADD_SUCCESS, true));
		
	}
	
	public Result listPost(String userId) {
		List<JsonNode> L1=new ArrayList<JsonNode>();
		try {
			L1=postImpl.listPost(userId);
		} catch (Exception e) {
			return badRequest(ConstructResponseUtil.createFailureResponsev1(null, e.getMessage(), false));
		}
		
		return ok(ConstructResponseUtil.createSuccessResponsev1(Json.toJson(L1),ResponseMessage.POST_FETCH_SUCCESS, true));
		
	}
	public Result likePost() {
		boolean flag=true;
		JsonNode json = request().body().asJson();
		try {		
			flag=postImpl.likePost(json);
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
