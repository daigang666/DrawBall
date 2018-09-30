package com.example.drawball;

import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

public class BallAI extends Thread implements Ballinte {

	// ����BallAI�࣬����ʵ���Զ�����С����߳�
	private Random rand;
	private ImageView imageView;
	private boolean stoppflag = true;
	private boolean aflag = true;
	private Context test;
	private MainListener ml;

	public BallAI(ImageView imageView, Context test, MainListener ml) {
		this.imageView = imageView;
		this.test = test;
		this.ml = ml;
		rand = new Random();
	}

	// ������̵߳�run���������߳���Ҫִ�еĴ���
	public void run() {

		while (aflag) {
			if (stoppflag) {
				float x = rand.nextInt(imageView.getWidth());
				float y = rand.nextInt(imageView.getHeight());
				float speedX = rand.nextInt(10) + 1;
				float speedY = rand.nextInt(10) + 1;
				float r = rand.nextInt(20) + 5;
				float blood = r;
				Paint paint = new Paint();
				paint.setARGB(255, rand.nextInt(255), rand.nextInt(255),
						rand.nextInt(255));

				// ʵ����С�����
				Ball ball = new Ball(x, y, r, speedX, speedY, blood, paint,
						false, test, ml);
				list.add(ball);// �����ɵ�С��洢�����������

				// System.out.println(list.size());
				try {// ��������0.5��
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean isAflag() {
		return aflag;
	}

	public void setAflag(boolean aflag) {
		this.aflag = aflag;
	}

	public void setStoppflag(boolean stoppflag) {
		this.stoppflag = stoppflag;
	}
}
