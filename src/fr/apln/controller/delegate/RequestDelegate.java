package fr.apln.controller.delegate;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.NameValuePair;

import android.os.AsyncTask;
import fr.apln.controller.utils.ErrorCode;
import fr.apln.controller.utils.ResultObject;
import fr.apln.controller.utils.TaskListener;

/**
 * Request delegate to create HTTP request
 * @author Thomas Thiebaud. Code from https://github.com/paulinemontmayeur/RandOnClient/blob/master/src/com/insa/randon/utilities/RequestExecutor.java
 *
 */
public class RequestDelegate extends AsyncTask<Void, Void, ResultObject> {		
	RequestType requestType;
	String url;
	TaskListener listener;
	String paramsStringJson;
	List<NameValuePair> paramsNameValue;

	
	/**
	 * RequestExecuter constructor
	 * @param params Json string to send, send null if it is not used
	 * @param url Url to call
	 * @param type Request type
	 * @param listener Listener to notify when the API call is finished
	 */
	public RequestDelegate(String params, String url, RequestType type, TaskListener listener){
		this.paramsStringJson = params;
		this.url = url;
		this.requestType = type;
		this.listener = listener;
		this.paramsNameValue=null;
	}
	
	public RequestDelegate(List<NameValuePair> paramsNameValue, String url, RequestType type, TaskListener listener){
		this.paramsStringJson = null;
		this.url = url;
		this.requestType = type;
		this.listener = listener;
		this.paramsNameValue=paramsNameValue;
	}
	
	
	@Override
	protected ResultObject doInBackground(Void... params) {
		ResultObject resultObject = null;	        
		if (requestType == RequestType.GET){
			resultObject = executeGET();
		} else if (requestType == RequestType.POST){
			resultObject = executePOST();
		}
		
		return resultObject;			
	}
	
	@Override
	protected void onPostExecute(ResultObject result) {
		super.onPostExecute(result);
		
		if (listener != null){
			if (result.getErrCode() == ErrorCode.OK){
				listener.onSuccess(result.getContent());
			} else {
				listener.onFailure(result.getErrCode());
			}
		}
	}
	
	/**
	 * Performs a Get request
	 * @return object containing request result
	 */
	private ResultObject executeGET(){
		HttpURLConnection urlConnection = null;
		ResultObject resultObject = null;
		
		try {
			//To test get method, you can use this link: "http://blogname.tumblr.com/api/read/json?num=5"
			if (paramsNameValue != null)
		    {
		    	url+="/?";
		    	for(int i=0; i<paramsNameValue.size(); i++)
		    	{
		    		url+=paramsNameValue.get(i).getName()+"="+paramsNameValue.get(i).getValue();
		    		if(i<paramsNameValue.size()-1)
		    		{
		    			url+="&";
		    		}
		    	}
		    }
			System.out.println(url);
			URL urlGet = new URL(url);
			urlConnection = (HttpURLConnection) urlGet.openConnection();
			
			//read response
			String response = "";
			try {
				response = readInputStream(urlConnection.getInputStream());
			} catch (FileNotFoundException e){
				e.printStackTrace();
			}
			int code = urlConnection.getResponseCode();
			System.out.println(code);
			if (code == HttpURLConnection.HTTP_OK){
				resultObject = new ResultObject(ErrorCode.OK, response);
			} else {
				resultObject = new ResultObject(ErrorCode.FAILED, "");
			}			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			resultObject = new ResultObject(ErrorCode.FAILED, "");
		} catch (IOException e) {
			e.printStackTrace();
			resultObject = new ResultObject(ErrorCode.FAILED, "");
		}  finally {
			if (urlConnection != null){
				urlConnection.disconnect();
			}
		}

		return resultObject;
	}
	
		
	/**
	 * Performs a POST request
	 * @return object containing request result
	 */
	private ResultObject executePOST(){
		
		HttpURLConnection urlConnection = null;
		ResultObject resultObject = null;
		
		try {
			//To test post method, you can use this link: "http://postcatcher.in/catchers/546f635e9ac9260200000109"
			//TODO : change with HttpsUrlConnection
			URL urlPost = new URL(url);
			urlConnection = (HttpURLConnection) urlPost.openConnection();
			urlConnection.setDoOutput(true);
		    urlConnection.setChunkedStreamingMode(0);
		    urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		    
		    if (paramsStringJson != null)
		    {
		    	OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
			    out.write(paramsStringJson.getBytes());
			    out.flush ();
			    out.close ();
		    }
		    
		    //read response
			String response = "";
			try {
				response = readInputStream(urlConnection.getInputStream());
			} catch (FileNotFoundException e){
				if (e != null){
					e.printStackTrace();
				}				
			}
					
			int code = urlConnection.getResponseCode();
			System.out.println(code);
			if (code == HttpURLConnection.HTTP_CREATED || code == HttpURLConnection.HTTP_OK){
				resultObject = new ResultObject(ErrorCode.OK, response);
			} else if (code == HttpURLConnection.HTTP_FORBIDDEN){
				resultObject = new ResultObject(ErrorCode.DENIED, "");				
			} else {
				resultObject = new ResultObject(ErrorCode.FAILED, "");
			}			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			resultObject = new ResultObject(ErrorCode.REQUEST_FAILED, "");
		} catch (IOException e) {
			e.printStackTrace();
			resultObject = new ResultObject(ErrorCode.REQUEST_FAILED, "");
		} finally {
			if (urlConnection != null){
				urlConnection.disconnect();
			}
		}

		return resultObject;
	}
	
	/**
	 * Return string from input stream
	 * @param in Input stream
	 * @return Content of input stream
	 * @throws IOException input stream error
	 */
	private static String readInputStream(InputStream in) throws IOException{
		InputStream bis = new BufferedInputStream(in);
		byte[] contents = new byte[1024];
		int bytesRead=0;
		String strFileContents = ""; 
		
		while( (bytesRead = bis.read(contents)) != -1){ 
			strFileContents += new String(contents, 0, bytesRead);               
		}
		return strFileContents;
	}	
	
	public enum RequestType{
		GET,
		POST	
	}
}