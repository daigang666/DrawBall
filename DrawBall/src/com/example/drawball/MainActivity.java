package com.example.drawball;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements Ballinte{
	 private SensorManager sensorMgr;   
	 private MainListener ml;
	 private ImageView imageView;
	 MyHandler handler;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imageView=(ImageView)this.findViewById(R.id.imageView);
		Button begin=(Button)this.findViewById(R.id.begin);
		Button stop=(Button)this.findViewById(R.id.stop);
		
		handler = new MyHandler(imageView);// 实例化hangdler对象
		ml=new MainListener(imageView,handler,this);
		
		begin.setOnClickListener(ml);
		stop.setOnClickListener(ml);
		//Ball ball=new Ball(this);
		Paint paint=new Paint();
		//实例化小球对象，作为自己的小球
		Ball myball=new Ball(imageView.getWidth()/2, imageView.getHeight()/2,20, 0, 0, 100, paint,true,this,ml);
		list.add(myball);
	
		
		sensorMgr = (SensorManager)getSystemService(SENSOR_SERVICE);  
				    
		  
		
	}
//	private Handler mHandler = new Handler() { 
//		@SuppressWarnings("unchecked") 
//		public void handleMessage(android.os.Message msg) { 
//		if (msg.what == 1) { 
//		// 相关需要调用的方法
//			
//		} 
//		}
//		}; 
		 
	@Override
    protected void onResume() {
		super.onResume();
		
		Sensor sensor = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		//注册listener，第三个参数是检测的精确度  
		sensorMgr.registerListener(ml, sensor, SensorManager.SENSOR_DELAY_GAME);
	}
				   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onDestroy(){
		BallAI ba=ml.getBa();
		ba.setStoppflag(false);
		ba.setAflag(false);
		MainThread mt=ml.getMt();
		mt.setStopflag(false);
		mt.setFlag(false);
		super.onDestroy();
		//sensorMgr.unregisterListener(ml)
	}
	
}
