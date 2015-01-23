package fr.apln.controller.services;

import static fr.apln.controller.services.Constants.JSON_USER_NAME;
import static fr.apln.controller.services.Constants.SERVICE_USER_CONNECT;
import static fr.apln.controller.services.Constants.URL_BASE;
import static fr.apln.controller.services.Constants.URL_USER;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import fr.apln.controller.delegate.RequestDelegate;
import fr.apln.controller.utils.ErrorCode;
import fr.apln.controller.utils.TaskListener;

public class UserServices {
	static final Gson gson = new Gson();
	
	/**
	 * Connect user
	 * @param username Name of the user
	 * @param listener Listener to notify when the account is created
	 */
	public static void connect(String name, TaskListener listener)
	{
		//build url
		String url = URL_BASE + URL_USER + SERVICE_USER_CONNECT;
		
		try {
			String jsonParams = "";
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty(JSON_USER_NAME, URLEncoder.encode(name, "UTF-8"));
			jsonParams = gson.toJson(jsonObject);
	
			new RequestDelegate(jsonParams, url, RequestDelegate.RequestType.POST, listener).execute();	
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			listener.onFailure(ErrorCode.FAILED);
		}	
	}
}
