package unicen.edu.gpsmoviles;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class GPSService extends Service {
	static final String MAXSPEED = "maxspeed";
	static final String MESSAGE = "message";
	
	private float speed;
	private static final float initial_speed = 0;
	private String provider;
	private LocationManager locMan;
	
	private LocationListener listener = new LocationListener(){
		@Override
		public void onLocationChanged(Location location) {
			if (location!=null){
				if (location.getSpeed()>speed)
					speed = location.getSpeed();
			}
		}
		@Override
		public void onProviderDisabled(String provider) {}
		@Override
		public void onProviderEnabled(String provider) {}
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {}
	};		
	private BroadcastReceiver receiver = new BroadcastReceiver(){
		@Override
		public void onReceive(Context context, Intent i){
			Bundle bundle = i.getExtras();
			if (bundle!=null){
				if (bundle.getBoolean(GPSActivity.RESTART)){
					speed = initial_speed;
				}
			}
			Intent result = new Intent(MESSAGE);				
			result.putExtra(MAXSPEED, speed);
			sendBroadcast(result);
		}
	};
	@Override
	public void onCreate(){
		speed= initial_speed;
		registerReceiver(receiver, new IntentFilter(GPSActivity.REQUEST));
		locMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		provider = locMan.getBestProvider(criteria, false);
		locMan.requestLocationUpdates(provider, 3000, 10,listener);
	}
	@Override 
	public int onStartCommand(Intent i, int flags, int id){
		return START_STICKY;
	}	
	@Override 
	public void onDestroy(){
		super.onDestroy();
		unregisterReceiver(receiver);
		locMan.removeUpdates(listener);
	}
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
}
