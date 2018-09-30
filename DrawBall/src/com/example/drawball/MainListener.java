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
	private boolean stopflag = false;// �Զ���С��ı�־λ
	private boolean myflag = true;// �жϽ�����ͣ��־λ
	private boolean stoppflag = false;// �Զ�����С��ı�־λ
	private Context test;
	private int k=0;

	public MainListener(ImageView imageView,MyHandler handler,Context test) {
		this.imageView = imageView;
		this.handler=handler;
		this.test=test;
		ba = new BallAI(imageView,test,this);// ʵ�����Զ�����С��Ķ���
		//handler = new MyHandler(imageView);// ʵ����hangdler����
		mt = new MainThread(handler);// ʵ�����Զ���С����߳�
	}

	public void onClick(View v) {

		if (v.getId() == R.id.begin) {
			imageView.setOnTouchListener(this);//��imageView��Ӽ���
			myflag = true;
			// stoppflag=true;
			if (k==0) {
				stopflag = true;// �Զ���С��ı�־λ
				stoppflag = true;// �Զ�����С��ı�־λ
				ba.setStoppflag(stoppflag);
				ba.start();// �������߳�
				mt.setStopflag(stopflag);
				mt.start();// �����߳�
				k++;

			} else if (k>0) {
				//mt.setFlag(false);
				mt.setStopflag(false);// ֹͣ��С��
				ba.setStoppflag(false);// ֹͣ����С��
				list.clear();// ����������
				Paint paint = new Paint();
				// ʵ����С�������Ϊ�Լ���С��
				Ball myball = new Ball(imageView.getWidth() / 2,
						imageView.getHeight() / 2, 20, 0, 0, 100, paint,true,test,this);
				list.add(myball);// �����Լ���С��

				ba.setAflag(true);
				mt.setFlag(true);
				ba.setStoppflag(true);// ��ʼ����С��
				mt.setStopflag(true);// ��ʼ��С��
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
//		//Log.i("������", " "+z);
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
