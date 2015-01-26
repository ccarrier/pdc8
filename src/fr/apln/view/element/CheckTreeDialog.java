package fr.apln.view.element;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import fr.apln.model.Tree;
import fr.apln.view.R;
import fr.apln.view.fragment.PlayFragment;

/**
 * Custom dialog to check the code of one tree
 * @author Thomas Thiebaud
 *
 */
public class CheckTreeDialog extends FullWidthTreeDialog {
	
	private PlayFragment fragment = null;
	
	/**
	 * Constructor
	 * @param context Context
	 * @param fragment Play fragment 
	 * @param tree Tree to check
	 */
	public CheckTreeDialog(Context context,PlayFragment fragment, Tree tree) {
		super(context,R.layout.check_tree_dialog,tree);

		this.fragment = fragment;
		
		findViewById(R.id.info_tree_ok).setOnClickListener(this);
		findViewById(R.id.info_tree_nok).setOnClickListener(this);	
		System.out.println(tree.getCode());
		this.show();
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.info_tree_ok:
			EditText edit = (EditText)findViewById(R.id.check_tree_edit);
			if(tree.getCode().equals("AR" + edit.getText())) {
				Toast.makeText(getContext(),R.string.code_correct, Toast.LENGTH_SHORT).show();
				fragment.updateTreeToFind();
				this.dismiss();
			}
			else
				Toast.makeText(getContext(),R.string.code_incorrect, Toast.LENGTH_SHORT).show();
			break;
		case R.id.info_tree_nok:
			this.dismiss();
			break;
		}
	}

}
