package unicen.edu.gpsmoviles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class BootReceiver extends BroadcastReceiver{

    SharedPreferences sharedPreferences;

	@Override
	public void onReceive(Context context, Intent i) {
		sharedPreferences = context.getSharedPreferences(GPSActivity.PREFERENCES,GPSActivity.MODE_MULTI_PROCESS);
		if (sharedPreferences.getBoolean(GPSService.BOOT_PREFERENCES,false)){
			Intent intent = new Intent(context,GPSService.class);
			context.startService(intent);
		}
	}

}
