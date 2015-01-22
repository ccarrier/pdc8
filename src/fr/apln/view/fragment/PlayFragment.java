package fr.apln.view.fragment;

import java.util.HashMap;
import java.util.Map;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.apln.controller.MainController;
import fr.apln.controller.services.RaceServices;
import fr.apln.controller.utils.ErrorCode;
import fr.apln.controller.utils.TaskListener;
import fr.apln.model.Tree;
import fr.apln.view.R;
import fr.apln.view.element.CheckTreeDialog;
import fr.apln.view.element.InfoTreeDialog;

public class PlayFragment extends Fragment implements OnMarkerClickListener, OnMapClickListener {
	private final LatLng PARC = new LatLng(45.778,4.855);
	private MapFragment fragment;
	private GoogleMap map;
	private Map<Marker, Tree> markers = new HashMap<Marker, Tree>();
	private Circle indicator = null;
	
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
		}

        //map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(PARC, 15));
		map.setOnMarkerClickListener(this);
		map.setOnMapClickListener(this);
		
		Tree tree = MainController.getInstance().getTreeToFind();
		indicator = map.addCircle(new CircleOptions()
			.center(new LatLng(tree.getLatitude(), tree.getLongitude()))
			.radius(30)
			.fillColor(0x44ff0000)
			.strokeColor(0xffff0000)
			.strokeWidth(2));
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

	@Override
	public boolean onMarkerClick(Marker marker) {
		Tree tree = markers.get(marker);
		new InfoTreeDialog(getActivity(),tree);
		return true;
	}

	@Override
	public void onMapClick(LatLng coord) {
		Tree t = MainController.getInstance().getTreeToFind();
		double d = MainController.getInstance().distance(coord.longitude, coord.latitude, t.getLongitude(), t.getLatitude());
	
		if(d <= 30.0) {
			new CheckTreeDialog(getActivity(),this, t);
		}
	}

	public void updateTreeToFind() {
		Tree t = MainController.getInstance().getTreeToFind();
		Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(t.getLatitude(), t.getLongitude())));
		markers.put(marker, t);
		indicator.remove();
		
		if(MainController.getInstance().updateTreeToFind()) {
			Tree tree = MainController.getInstance().getTreeToFind();
			indicator = map.addCircle(new CircleOptions()
				.center(new LatLng(tree.getLatitude(), tree.getLongitude()))
				.radius(30)
				.fillColor(0x44ff0000)
				.strokeColor(0xffff0000)
				.strokeWidth(2));
		}
		else {
			TaskListener addRaceTimeListener =  new TaskListener() {
				
				@Override
				public void onSuccess(String content) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onFailure(ErrorCode errCode) {
					// TODO Auto-generated method stub
					
				}
			};
			
			RaceServices.addTime(addRaceTimeListener);
			
			Toast.makeText(getActivity(),"Course terminée : " + MainController.getInstance().getTotalTime(), Toast.LENGTH_SHORT).show();
		}
	}
}
	