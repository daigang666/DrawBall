package com.example.drawball;

import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

public class BallAI extends Thread implements Ballinte {

	// 定义BallAI类，用来实现自动生成小球的线程
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

	// 定义此线程的run方法，是线程需要执行的代码
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

				// 实例化小球对象
				Ball ball = new Ball(x, y, r, speedX, speedY, blood, paint,
						false, test, ml);
				list.add(ball);// 将生成的小球存储到数组队列中

				// System.out.println(list.size());
				try {// 设置休眠0.5秒
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
