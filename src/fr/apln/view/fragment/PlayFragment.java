package fr.apln.view.fragment;

import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import fr.apln.modele.entite.Arbre;
import fr.apln.services.Services;
import fr.apln.view.R;

public class PlayFragment extends Fragment {
	private final LatLng PARC_TETE_OR = new LatLng(45.777403, 4.855214);
	private MapFragment fragment;
	private GoogleMap map;
	
	TextView timerTextView;
    long startTime = 0;
    long startPauseTime = 0;
    
  //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timerTextView.setText(String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);
        }
    };
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_play, container, false);
		
		timerTextView = (TextView) view.findViewById(R.id.timerTextView);

        Button b = (Button) view.findViewById(R.id.start_stop_button);
        b.setText("start");
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                if (b.getText().equals("stop")) {
                	startPauseTime = System.currentTimeMillis();
                    timerHandler.removeCallbacks(timerRunnable);
                    b.setText("start");
                } else {
                	if(startTime == 0)
                		startTime = System.currentTimeMillis();
                	else
                		startTime += System.currentTimeMillis() - startPauseTime;
                    timerHandler.post(timerRunnable);
                    b.setText("stop");
                }
            }
        });
		
		return view;
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
			map.addMarker(new MarkerOptions().position(PARC_TETE_OR));
			map.animateCamera(CameraUpdateFactory.newLatLngZoom(PARC_TETE_OR, 15.0f));
			//
			setDefautCircuit();
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
        
        timerHandler.removeCallbacks(timerRunnable);
        
        super.onDestroyView();
	}
	
	
	private void setDefautCircuit()
	{
		Services s = new Services();
		List<Arbre> arbres = s.genererTestArbres();
		PolylineOptions p = new PolylineOptions();
		for (Arbre arbre : arbres) {
			p.add(new LatLng(arbre.getLatitude(), arbre.getLongitude()));
			 map.addMarker(new MarkerOptions()
             .position(new LatLng(arbre.getLatitude(), arbre.getLongitude()))
             .title(arbre.getCode())
             .snippet("Population: " + arbre.getCode())
             .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
		}
		p.add(new LatLng(arbres.get(0).getLatitude(), arbres.get(0).getLongitude()));
		
		map.addPolyline(p);
	}
}
	