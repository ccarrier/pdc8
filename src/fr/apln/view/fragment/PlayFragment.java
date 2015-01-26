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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.apln.controller.MainController;
import fr.apln.controller.listener.AddRaceTimeListener;
import fr.apln.controller.services.RaceServices;
import fr.apln.model.Tree;
import fr.apln.view.R;
import fr.apln.view.element.CheckTreeDialog;
import fr.apln.view.element.InfoTreeDialog;

/**
 * Play view
 * @author Thomas Thiebaud
 *
 */
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
		
		if (map == null)
			map = fragment.getMap();

        //map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(PARC, 15));
		map.setOnMarkerClickListener(this);
		map.setOnMapClickListener(this);
		
		MainController.getInstance().resetRace();
		Tree tree = MainController.getInstance().getTreeToFind();
		this.drawCircle(tree.getLatitude(), tree.getLongitude());
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

	/**
	 * Display new tree to find.
	 */
	public void updateTreeToFind() {
		Tree t = MainController.getInstance().getTreeToFind();
		this.drawMarker(t);
		
		if(MainController.getInstance().updateTreeToFind()) {
			Tree tree = MainController.getInstance().getTreeToFind();
			this.drawCircle(tree.getLatitude(), tree.getLongitude());
		}
		else {			
			RaceServices.addTime(new AddRaceTimeListener());
			Toast.makeText(getActivity(),"Course terminée", Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * Draw a circle on the map
	 * @param lat Latitude of the center
	 * @param lon Longitude of the center
	 */
	public void drawCircle(double lat,double lon) {
		indicator = map.addCircle(new CircleOptions()
			.center(new LatLng(lat,lon))
			.radius(30)
			.fillColor(0x44000000)
			.strokeColor(0xff000000)
			.strokeWidth(2));
	}
	
	/**
	 * Draw a circle on the map
	 * @param lat Latitude of the center
	 * @param lon Longitude of the center
	 */
	public void drawMarker(Tree tree) {
		Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(tree.getLatitude(), tree.getLongitude())));
		marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_tree_black));
		markers.put(marker, tree);
		indicator.remove();
	}
}
	