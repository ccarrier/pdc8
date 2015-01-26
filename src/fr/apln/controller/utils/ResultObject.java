package fr.apln.controller.utils;

import android.text.Html;

/**
 * @author Thomas Thiebaud
 * Represent a result of a request
 */
public class ResultObject {

	private ErrorCode errCode;
	private String content = "";

	/**
	 * Constructor
	 * @param errCode Error code
	 * @param content Content
	 */
	public ResultObject(ErrorCode errCode, String content) {
		this.errCode = errCode;
		this.content = Html.fromHtml(content).toString();;
	}

	/**
	 * Get error code
	 * @return Error code
	 */
	public ErrorCode getErrCode() {
		return errCode;
	}

	/**
	 * Get content
	 * @return Content
	 */
	public String getContent() {
		return content;
	}
	
}