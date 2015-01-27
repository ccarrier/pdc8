package fr.apln.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import fr.apln.view.R;

/**
 * Rules view
 * @author Thomas Thiebaud
 *
 */
public class RulesFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_rules, container, false);

		WebView mWebView = (WebView) v.findViewById(R.id.webview);    

		String text = "<html><body>"
				+ "<h2>" + getString(R.string.rules_h1) + "</h2>"
				+ "<p align=\"justify\">" + getString(R.string.rules_p1) + "</p> "
				+ "<h2>" + getString(R.string.rules_h2) + "</h2>"
				+ "<p align=\"justify\">" + getString(R.string.rules_p2) + "</p> "
				+ "<p align=\"justify\">" + getString(R.string.rules_p3) + "</p> "
				+ "<p align=\"justify\">" + getString(R.string.rules_p4) + "</p> "
				+ "</body></html>";
		
		mWebView.loadDataWithBaseURL(null,text, "text/html", "utf-8",null);
		
		return v;
	}
}
