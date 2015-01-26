package fr.apln.controller.listener;

import static fr.apln.controller.services.Constants.JSON_USER_ID;
import static fr.apln.controller.services.Constants.JSON_USER_NAME;

import org.json.JSONException;
import org.json.JSONObject;

import fr.apln.controller.MainController;
import fr.apln.controller.utils.ErrorCode;
import fr.apln.controller.utils.TaskListener;
import fr.apln.model.User;

/**
 * @author thomasthiebaud
 * Connect listener in order to connect an user an get informations from database
 */
public class ConnectListener implements TaskListener{

	@Override
	public void onSuccess(String content) {
		try {
			JSONObject object = new JSONObject(content);
			JSONObject u = object.getJSONObject("content");
			
			User user = new User();
			user.setId(u.getString(JSON_USER_ID));
			user.setName(u.getString(JSON_USER_NAME));
			
			MainController.getInstance().setCurrentUser(user);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFailure(ErrorCode errCode) {
		System.err.println(errCode.toString());
	}

}
