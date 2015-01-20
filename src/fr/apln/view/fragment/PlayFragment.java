package fr.apln.view.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.apln.view.R;

public class PlayFragment extends Fragment {
	private final LatLng HAMBURG = new LatLng(53.558, 9.927);
	private MapFragment fragment;
	private GoogleMap map;
	
	TextView timerTextView;
    long startTime = 0;
    
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
                    timerHandler.removeCallbacks(timerRunnable);
                    b.setText("start");
                } else {
                    startTime = System.currentTimeMillis();
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
        
        timerHandler.removeCallbacks(timerRunnable);
        
        super.onDestroyView();
	}
}
	