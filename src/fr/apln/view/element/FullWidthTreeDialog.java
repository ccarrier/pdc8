package fr.apln.view.element;

import android.app.Dialog;
import android.content.Context;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import fr.apln.model.Tree;
import fr.apln.view.R;

/**
 * Represents a full width dialog with a reference to a tree
 * @author Thomas Thiebaud
 *
 */
public abstract class FullWidthTreeDialog extends Dialog implements OnClickListener{
	protected Tree tree = null;
	
	/**
	 * Constructor
	 * @param context Context
	 * @param view Content view 
	 * @param tree Tree to check
	 */
	public FullWidthTreeDialog(Context context, int view, Tree tree) {
		super(context);

		this.tree = tree;
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(view);
		
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		Window window = this.getWindow();
		lp.copyFrom(window.getAttributes());
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		window.setAttributes(lp);
		
		TextView tv;
		tv = (TextView)findViewById(R.id.popup_title);
		tv.setText(tree.getName());
		tv = (TextView)findViewById(R.id.popup_snippet_height);
		tv.setText(tree.getHeight()+" m");
		tv = (TextView)findViewById(R.id.popup_snippet_trunk);
		tv.setText(tree.getTrunkDiameter()+" m");
		tv = (TextView)findViewById(R.id.popup_snippet_crown);
		tv.setText(tree.getCrownDiameter()+" m");
		tv = (TextView)findViewById(R.id.popup_snippet_genre);
		tv.setText(tree.getGenre());
		tv = (TextView)findViewById(R.id.popup_snippet_species);
		tv.setText(tree.getSpecies());
		tv = (TextView)findViewById(R.id.popup_snippet_type);
		tv.setText(tree.getType());
	}
}
