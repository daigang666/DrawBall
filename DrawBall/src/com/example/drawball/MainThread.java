package com.example.drawball;

//import android.os.Handler;
import android.os.Message;

//import android.widget.ImageView;

public class MainThread extends Thread implements Ballinte {
	// private ImageView imageView;
	private boolean flag=true;
	private boolean stopflag=false;
	MyHandler handler;

	public MainThread(MyHandler handler) {
		this.handler = handler;
		// this.imageView = imageView;
		// this.flag=flag;
	}

	public void run() {
		while (flag) {
			if (stopflag) {
				Message msg = new Message();
				msg.what = 1;
				handler.sendMessage(msg);

				// imageView.setImageBitmap(bitmap);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				handler.setBitmap(null);
			}
		}
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public void setStopflag(boolean stopflag) {
		this.stopflag = stopflag;
	}

}
