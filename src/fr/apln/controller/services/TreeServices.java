package fr.apln.controller.services;

import static fr.apln.controller.services.Constants.SERVICE_TREE_ALL;
import static fr.apln.controller.services.Constants.URL_BASE;
import static fr.apln.controller.services.Constants.URL_TREE;

import com.google.gson.Gson;

import fr.apln.controller.delegate.RequestDelegate;
import fr.apln.controller.utils.TaskListener;

/**
 * @author Thomas Thiebaud
 * Tree services to manage operations about trees.
 */
public class TreeServices {
	static final Gson gson = new Gson();

	/**
	 * Get all trees
	 * @param listener Tree listener
	 */
	public static void all(TaskListener listener) {
		String url = URL_BASE + URL_TREE + SERVICE_TREE_ALL;
		new RequestDelegate("", url, RequestDelegate.RequestType.GET, listener).execute();
	}
}
