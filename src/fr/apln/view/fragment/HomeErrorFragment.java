package fr.apln.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.apln.view.R;

/**
 * Home view (with errors)
 * @author Thomas Thiebaud
 *
 */
public class HomeErrorFragment extends Fragment {
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_error, container, false);
    }
}
