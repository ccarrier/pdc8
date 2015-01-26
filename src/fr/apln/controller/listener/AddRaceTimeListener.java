package fr.apln.controller.listener;

import fr.apln.controller.utils.ErrorCode;
import fr.apln.controller.utils.TaskListener;

/**
 * @author Thomas Thiebaud
 * Add race tie listener in order to save time in database
 */
public class AddRaceTimeListener implements TaskListener {

	@Override
	public void onSuccess(String content) {
		System.out.println(content);
	}

	@Override
	public void onFailure(ErrorCode errCode) {
		System.err.println(errCode);
	}
}
