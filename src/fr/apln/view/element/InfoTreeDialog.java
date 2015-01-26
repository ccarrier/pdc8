package fr.apln.view.element;

import android.content.Context;
import android.view.View;
import fr.apln.model.Tree;
import fr.apln.view.R;

/**
 * Custom dialog to print informations of one tree
 * @author Thomas Thiebaud
 *
 */
public class InfoTreeDialog extends FullWidthTreeDialog {

	/**
	 * Constructor
	 * @param context Context
	 * @param tree Tree to display
	 */
	public InfoTreeDialog(Context context,Tree tree) {
		super(context,R.layout.info_tree_dialog,tree);
		findViewById(R.id.info_tree_nok).setOnClickListener(this);
		this.show();
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.info_tree_nok:
			this.dismiss();
			break;
		}
	}

}
