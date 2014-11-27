package unicen.edu.gpsmoviles;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
	static final String VELOCITY = "velocity";
	static final String PREFERENCES = "preferences";
	private static final String unidades = "\n m/s";
	
	SharedPreferences sharedPreferences;
	Editor editor;
	
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
				sharedPreferences = getSharedPreferences(GPSActivity.PREFERENCES,MODE_MULTI_PROCESS);
				editor = sharedPreferences.edit();
				editor.putFloat(VELOCITY, 0f);
				editor.commit();
			}
		});
		start = (Button) findViewById(R.id.startbt);
		start.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(GPSActivity.this,GPSService.class);
				startService(i);
			}
		});
		showVelocity = (Button) findViewById(R.id.showbt);
		showVelocity.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				sharedPreferences = getSharedPreferences(GPSActivity.PREFERENCES,MODE_MULTI_PROCESS);
				setVelocity(sharedPreferences.getFloat(VELOCITY, 0f));
			}
		});	
	}
	private void setVelocity(Float speed){
		sb.append(df.format(speed));
		sb.append(unidades);
		this.textView.setText(sb);
		sb.delete(0, sb.length());
	}
}