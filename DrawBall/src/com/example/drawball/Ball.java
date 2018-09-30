package com.example.drawball;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

public class Ball{ //implements Ballinte{
	// ����Ball�࣬��������Ball�����Ժͷ���
	public float x, y, r, speedX, speedY, blood;
	private Paint paint;
	private boolean flag;
	Handler mHandler;
	private Context context;
	private MainThread mt;
	private BallAI ba;
	private MainListener ml;
	
	public Ball(float x, float y, float r, float speedX,float speedY,
			float blood,Paint paint,boolean flag,Context test,MainListener ml) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.speedX = speedX;
		this.speedY = speedY;
		this.blood = blood;
		this.paint=paint;
		this.flag=flag;
		this.context = test;  
		this.ml=ml;
		//ml=new MainListener(null, null, test);
	}
	// ���廭С��ķ���
	public void drawBall(Canvas canvas) {
		canvas.drawCircle(x, y, r, paint);
//		paint.setColor(Color.red);
//		paint.drawString("" + blood + "", x + speedX - 3, y + speedY + 5);
		
		x += speedX;
		y += speedY;
	}

	// ����С���ƶ��ķ�������������߽��ص�
	public void turnBall(ImageView imageView) {
		// System.out.println(bm+"=========");
		//ImageView imageView=(ImageView)this
		
		if (imageView != null)
			if (x >= imageView.getWidth()) {
				speedX *= -1;
			} else if (x <= 0) {
				speedX *= -1;
			}
		if (imageView != null)
			if (y >= imageView.getHeight()) {
				speedY *= -1;
			} else if (y <= 0) {
				speedY *= -1;
			}

	}
	public void winball(ArrayList<Ball> list){
		Ball myball= list.get(0);
		if(myball.flag){
			if(myball.r>100){
				Log.i("winball", "�ж�Ӯ�ˣ�");
				ba=ml.getBa();
				ba.setStoppflag(false);//��ͣ�Զ���������߳�
				mt=ml.getMt();
				mt.setStopflag(false);//��ͣ�Զ���С����߳� 
				ml.yichu();
				list.clear();
				AlertDialog.Builder dialogquit=new AlertDialog.Builder(context);
				dialogquit.setTitle("��ʾ");
				dialogquit.setMessage("��Ӯ�ˣ�");
				dialogquit.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						//System.exit(0);
					}
				});
//				dialogquit.setNegativeButton("û���ֻ�", new DialogInterface.OnClickListener() {
//					
//					@Override
//					public void onClick(DialogInterface dialog, int which) {						
//					}
//				});
				dialogquit.show();
			}
		}
		else{
			Log.i("winball", "�ж����ˣ�");
			ba=ml.getBa();
			ba.setStoppflag(false);//��ͣ�Զ���������߳�
			mt=ml.getMt();
			mt.setStopflag(false);//��ͣ�Զ���С����߳� 
			ml.yichu();
			list.clear();
			AlertDialog.Builder dialogquit=new AlertDialog.Builder(context);
			dialogquit.setTitle("��ʾ");
			dialogquit.setMessage("���Ѿ����ˣ�");
			dialogquit.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					//System.exit(0);
				}
			});
//			dialogquit.setNegativeButton("û���ֻ�", new DialogInterface.OnClickListener() {
//				
//				@Override
//				public void onClick(DialogInterface dialog, int which) {						
//				}
//			});
			dialogquit.show();
		}
	}
	// ����С�����ײʱ˭��˭�ķ���
	public void pzBall(ArrayList<Ball> list) {
		for (int i = 0; i < list.size(); i++) {
			Ball ball = list.get(i);
			if (ball != this) {// �жϲ��Ǻ��Լ����������ײ
				float d = (x - ball.x) * (x - ball.x) + (y - ball.y)
						* (y - ball.y);// ��������Բ�ľ����ƽ��
				if (d < (ball.r) * (ball.r) || d < r * r) {

					if (r > ball.r) {
						// r+=10;
						r += Math.sqrt(r * r + (ball.r) * (ball.r)) - r;
						list.remove(ball);
						Log.i("pzball", "r>ball.r ��");
						if(flag){
							this.winball(list);
						}else{
							this.winball(list);
						}

					} else if (ball.r > r) {
						// r+=10;

						ball.r += Math.sqrt(r * r + (ball.r) * (ball.r))
								- ball.r;
						list.remove(this);
						Log.i("pzball", "ball.r>r ��");
						if(ball.flag){
							this.winball(list);
						}else{
							this.winball(list);
						}
					}


				}
			}
		}
	}
}
