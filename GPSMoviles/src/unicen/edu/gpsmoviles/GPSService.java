package unicen.edu.gpsmoviles;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class GPSService extends Service {
	private float speed;
	static final String MAXSPEED = "maxspeed";
	private static final float initial_speed = 0;
	static final String MESSAGE = "message";
	
	
	private BroadcastReceiver receiver = new BroadcastReceiver(){
		@Override
		public void onReceive(Context context, Intent i){
			// en este caso no te importa que datos vienen, es solo una directiva que te llega de que 
			// tenes que decirle a la activity que velocidad hay actualmente
			Intent result = new Intent(MESSAGE);
			// este calculo en realidad hay que hacerlo afuera de este metodo.. se debe actualizar solo
			Double r = Math.random()*100;
			Float re = Float.parseFloat(r.toString());
			if (re>speed)
				speed = re;
			// solo hay que hacer esto
			result.putExtra(MAXSPEED, speed);
			sendBroadcast(result);
		}
	};
	
	@Override
	public void onCreate(){
		// se invoca cuando se llama al startService por primera vez
		speed= initial_speed;
	}
	@Override 
	public int onStartCommand(Intent i, int flags, int id){
		// se invoca siempre que se llama al startService (este comenzado o no el servicio)
		Log.d("debugging","onstartcommand");
		registerReceiver(receiver, new IntentFilter(GPSActivity.REQUEST));
		handleCommand(i);
		return START_STICKY;
	}	
	private void handleCommand(Intent i){
		Log.d("debugging","handlecommand");
	}

	@Override 
	public void onDestroy(){
		super.onDestroy();
		unregisterReceiver(receiver);
		Log.d("debugging","destroyservice");
	}
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
}
