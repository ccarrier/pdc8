package fr.apln.view.element;

import android.content.Context;
import android.view.View;
import fr.apln.model.Tree;
import fr.apln.view.R;


public class InfoTreeDialog extends FullWidthTreeDialog {

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
