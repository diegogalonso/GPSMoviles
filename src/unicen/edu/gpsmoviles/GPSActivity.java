package unicen.edu.gpsmoviles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GPSActivity extends Activity {
	private TextView textView ;
	private Button start;
	private Button stop;
	private Button restart;
	private Button showVelocity;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps);
		textView = (TextView) findViewById(R.id.textView);
		
		start = (Button) findViewById(R.id.startbt);
		start.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Log.d("debugging","click start");
				Intent i = new Intent(GPSActivity.this,GPSService.class);
				GPSActivity.this.textView.setText(R.string.textView);
				startService(i);
			}
		});
		stop = (Button) findViewById(R.id.stopbt);
		stop.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Log.d("debugging","click stop");
				Intent i = new Intent(GPSActivity.this,GPSService.class);
				stopService(i);
			}
		});
		
		showVelocity = (Button) findViewById(R.id.showbt);
		showVelocity.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Log.d("debugging","click show");
				GPSActivity.this.textView.setText("400 \n Km/h");
			}
		});
		
		restart = (Button) findViewById(R.id.restartbt);
		restart.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Log.d("debugging","click restart");
				Intent i = new Intent(GPSActivity.this,GPSService.class);
				GPSActivity.this.textView.setText(R.string.textView);
				startService(i);
			}
		});
		
	}
	/*
	private LocationListener listener = new LocationListener(){
		@Override
		public void onLocationChanged(Location location) {
			tv.setText("Lat: "+location.getLatitude()+" Long : "+location.getLongitude());
			//			|								  |
			// EVITAR ESTOS STRING HARCODEADOS
		}
		@Override
		public void onStatusChanged(String provider, int status,
				Bundle extras) {
		}
		@Override
		public void onProviderEnabled(String provider) {
		}
		@Override
		public void onProviderDisabled(String provider) {
		}
	};	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps);
		this.tv = (TextView) findViewById(R.id.text);
	}

	@Override 
	public void onResume(){
		super.onResume();
		LocationManager locMan = (LocationManager) getSystemService(LOCATION_SERVICE);
		locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 20, listener);
	}
	@Override 
	public void onPause(){
		super.onPause();
		LocationManager locMan = (LocationManager) getSystemService(LOCATION_SERVICE);
		locMan.removeUpdates(listener);
	}
	*/
}