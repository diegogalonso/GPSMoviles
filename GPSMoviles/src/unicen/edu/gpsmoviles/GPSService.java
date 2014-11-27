package unicen.edu.gpsmoviles;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class GPSService extends Service{
	static final String BOOT_PREFERENCES = "boot_preferences";

	private String provider;
	private LocationManager locMan;
	
	SharedPreferences sharedPreferences;
	Editor editor;
	
	private LocationListener listener = new LocationListener(){
		@Override
		public void onLocationChanged(Location location) {
			if (location!=null){
				sharedPreferences = getSharedPreferences(GPSActivity.PREFERENCES,GPSActivity.MODE_MULTI_PROCESS);
				if (location.getSpeed() > sharedPreferences.getFloat(GPSActivity.VELOCITY,0f)){
					editor = sharedPreferences.edit();
					editor.putFloat(GPSActivity.VELOCITY, location.getSpeed() );
					editor.commit();
				}
			}
		}
		@Override
		public void onProviderDisabled(String provider) {}
		@Override
		public void onProviderEnabled(String provider) {}
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {}
	};		
	@Override
	public void onCreate(){
		locMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		provider = LocationManager.GPS_PROVIDER;
		locMan.requestLocationUpdates(provider, 3000, 2,listener);
		sharedPreferences = getSharedPreferences(GPSActivity.PREFERENCES,GPSActivity.MODE_MULTI_PROCESS);

		editor = sharedPreferences.edit();
		editor.putFloat(GPSActivity.VELOCITY, 0f);
		editor.commit();
	}
	@Override 
	public int onStartCommand(Intent i, int flags, int id){
		editor = sharedPreferences.edit();
		editor.putBoolean(BOOT_PREFERENCES, true);
		editor.commit();
		return START_STICKY;
	}	
	@Override 
	public void onDestroy(){
		super.onDestroy();
		locMan.removeUpdates(listener);
		editor = sharedPreferences.edit();
		editor.putBoolean(BOOT_PREFERENCES, false);
		editor.commit();
	}
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
}
