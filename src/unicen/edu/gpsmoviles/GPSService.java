package unicen.edu.gpsmoviles;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class GPSService extends Service {
	
	@Override 
	public int onStartCommand(Intent i, int flags, int id){
		Log.d("debugging","onstartcommand");
		handleCommand(i);
		return START_STICKY;
	}	
	private void handleCommand(Intent i){
		Log.d("debugging","handlecommand");
	}

	@Override 
	public void onDestroy(){
		super.onDestroy();
		Log.d("debugging","destroyservice");
	}
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}
