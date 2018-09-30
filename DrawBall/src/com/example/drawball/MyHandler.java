package com.example.drawball;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class MyHandler extends Handler implements Ballinte {
	private Bitmap bitmap;// λͼ
	private Paint paint;// ����
	private Canvas canvas;// �ṩ��ͼ����
	private ImageView imageView;

	public MyHandler(ImageView imageView) {
		super();
		this.imageView = imageView;
	}

	// ��д������Ϣ�ķ���
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		switch (msg.what) {
		case 1:
			if (bitmap == null) {
				bitmap = Bitmap.createBitmap(imageView.getWidth(),
						imageView.getHeight(), Bitmap.Config.ARGB_8888);
				canvas = new Canvas(bitmap);
				paint = new Paint();
				paint.setColor(Color.BLACK);
			}
			for (int i = 0; i < list.size(); i++) {
				Ball ball = list.get(i);
				ball.drawBall(canvas);
				ball.turnBall(imageView);
				ball.pzBall(list);
				//ball.winball(list);
			}
			imageView.setImageBitmap(bitmap);
			break;

		}

	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

}
