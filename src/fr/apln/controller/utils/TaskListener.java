package fr.apln.controller.utils;

/**
 * @author Thomas Thiebaud
 * 
 */
public interface TaskListener {
	public void onSuccess(String content);
	public void onFailure(ErrorCode errCode);
}
