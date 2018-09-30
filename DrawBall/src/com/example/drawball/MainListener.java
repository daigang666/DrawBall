package com.example.drawball;

import android.content.Context;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainListener implements OnClickListener, OnTouchListener,
		Ballinte, SensorEventListener {
	private ImageView imageView;
	private float moveX, moveY, x, y, z;
	private Ball myball;
	MyHandler handler;
	private MainThread mt;
	private BallAI ba;
	private boolean stopflag = false;// 自动画小球的标志位
	private boolean myflag = true;// 判断交替暂停标志位
	private boolean stoppflag = false;// 自动产生小球的标志位
	private Context test;
	private int k=0;

	public MainListener(ImageView imageView,MyHandler handler,Context test) {
		this.imageView = imageView;
		this.handler=handler;
		this.test=test;
		ba = new BallAI(imageView,test,this);// 实例化自动产生小球的对象
		//handler = new MyHandler(imageView);// 实例化hangdler对象
		mt = new MainThread(handler);// 实例化自动画小球的线程
	}

	public void onClick(View v) {

		if (v.getId() == R.id.begin) {
			imageView.setOnTouchListener(this);//给imageView添加监听
			myflag = true;
			// stoppflag=true;
			if (k==0) {
				stopflag = true;// 自动画小球的标志位
				stoppflag = true;// 自动产生小球的标志位
				ba.setStoppflag(stoppflag);
				ba.start();// 启动该线程
				mt.setStopflag(stopflag);
				mt.start();// 启动线程
				k++;

			} else if (k>0) {
				//mt.setFlag(false);
				mt.setStopflag(false);// 停止画小球
				ba.setStoppflag(false);// 停止产生小球
				list.clear();// 清空数组队列
				Paint paint = new Paint();
				// 实例化小球对象，作为自己的小球
				Ball myball = new Ball(imageView.getWidth() / 2,
						imageView.getHeight() / 2, 20, 0, 0, 100, paint,true,test,this);
				list.add(myball);// 保存自己的小球

				ba.setAflag(true);
				mt.setFlag(true);
				ba.setStoppflag(true);// 开始产生小球
				mt.setStopflag(true);// 开始画小球
			}
			// else if(stopflag == false&&myflag==false){
			// stopflag = true;
			// mt.setStopflag(stopflag);
			// }

		} else if (v.getId() == R.id.stop) {
			if (myflag) {
				mt.setStopflag(false);
				ba.setAflag(false);
				myflag = false;
			} else {
				mt.setStopflag(true);
				ba.setAflag(true);
				myflag = true;
			}
		}

	}

	public boolean onTouch(View v, MotionEvent event) {
		myball = list.get(0);
		switch (event.getAction()) {

		case MotionEvent.ACTION_MOVE:
			moveX = event.getX();
			moveY = event.getY();
			myball.x = moveX;
			myball.y = moveY;
			break;
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	public void onSensorChanged(SensorEvent e) {
//		myball = list.get(0);
//		x = e.values[SensorManager.DATA_X];
//		y = e.values[SensorManager.DATA_Y];
//		z = e.values[SensorManager.DATA_Z];
//		//Log.i("传感器", " "+z);
//		myball.turnBall(imageView);
////		myball.speedX= -x*2;
////		myball.speedY= y*2;
//		myball.x+=-x*(myball.speedX+1);
//		myball.y+=y*(myball.speedY+1);
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}
	public void yichu(){
		imageView.setOnTouchListener(null);
	}
	public MainThread getMt() {
		return mt;
	}

	public void setMt(MainThread mt) {
		this.mt = mt;
	}

	public BallAI getBa() {
		return ba;
	}

	public void setBa(BallAI ba) {
		this.ba = ba;
	}
}
