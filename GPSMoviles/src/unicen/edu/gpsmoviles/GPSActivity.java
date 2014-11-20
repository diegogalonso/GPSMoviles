package unicen.edu.gpsmoviles;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
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
	private StringBuffer sb = new StringBuffer();
	private DecimalFormat df = new DecimalFormat("###.##");
	static final String REQUEST = "request";
	static final String RESTART = "restart";
	private static final String unidades = "\n m/s";
		
	private BroadcastReceiver receiver = new BroadcastReceiver(){
		@Override
		public void onReceive(Context context, Intent i){
			Bundle bundle = i.getExtras();
			if (bundle!=null){
				Float speed = bundle.getFloat(GPSService.MAXSPEED);
				setVelocity(speed);
			}
		}
	};
	private void setVelocity(Float speed){
		sb.append(df.format(speed));
		sb.append(unidades);
		this.textView.setText(sb);
		sb.delete(0, sb.length());
	}
	 @Override
	  protected void onResume() {
	    super.onResume();
	    registerReceiver(receiver, new IntentFilter(GPSService.MESSAGE));
	  }
	  @Override
	  protected void onPause() {
	    super.onPause();
	    unregisterReceiver(receiver);
	  }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps);
		textView = (TextView) findViewById(R.id.textView);
		stop = (Button) findViewById(R.id.stopbt);
		stop.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(GPSActivity.this,GPSService.class);
				stopService(i);
			}
		});
		restart = (Button) findViewById(R.id.restartbt);
		restart.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(REQUEST);
				i.putExtra(RESTART, true);
				sendBroadcast(i);
			}
		});
		start = (Button) findViewById(R.id.startbt);
		start.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(GPSActivity.this,GPSService.class);
				startService(i);
				Intent req = new Intent(REQUEST);
				sendBroadcast(req);
			}
		});
		showVelocity = (Button) findViewById(R.id.showbt);
		showVelocity.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(REQUEST);
				sendBroadcast(i);
			}
		});	
	}
}