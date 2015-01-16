package fr.apln.view.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.apln.view.R;

public class PlayFragment extends Fragment {
	private final LatLng HAMBURG = new LatLng(53.558, 9.927);
	private MapFragment fragment;
	private GoogleMap map;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_play, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		FragmentManager fm = getChildFragmentManager();
		fragment = (MapFragment) fm.findFragmentById(R.id.map_container);
		if (fragment == null) {
			fragment = MapFragment.newInstance();
			fm.beginTransaction().replace(R.id.map_container, fragment).commit();
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (map == null) {
			map = fragment.getMap();
			map.addMarker(new MarkerOptions().position(HAMBURG));
		}
	}
	
	@Override
	public void onDestroyView() {
        MapFragment f = (MapFragment) getFragmentManager().findFragmentById(R.id.map_container);

        if (f != null) {
            try {
                getFragmentManager().beginTransaction().remove(f).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        super.onDestroyView();
	}
}
	