package com.player;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Time;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class LiveLessonPlayActivity extends Activity {
	private int count;
	private long firClick;
	private long secClick;
	private MediaPlayer mediaPlayer1;
	private LinearLayout ll1;
	private LinearLayout ll2;
	private LinearLayout ll3;
	private LinearLayout llRight;
	private SurfaceView sView1;
	private SurfaceView sView2;
	private SurfaceView sView3;
	private MediaPlayer mediaPlayer2;
	private MediaPlayer mediaPlayer3;
	// 加载滚动圈
	private ProgressDialog pd = null;
	private Boolean pd1 = false;
	private Boolean pd2 = false;
	private Boolean pd3 = false;
	
	private Boolean bl = false;

	// 退出程序所用变量
	private int isExit = 0;
	private long lasttime = 0;

	/**
	 * 切换横竖屏方法
	 */
	private void switchScreen(LinearLayout surfHide1, LinearLayout surfHide2,
			LinearLayout surfOn,SurfaceView svH1,SurfaceView svH2,SurfaceView svV,LinearLayout llRight) {
		
		if (bl) {
			 iniplay();
			// 取消全屏
			surfHide1.setVisibility(View.VISIBLE);// 1
			surfHide2.setVisibility(View.VISIBLE);// 2
			svH1.setVisibility(View.VISIBLE);
			svH2.setVisibility(View.VISIBLE);
			surfOn.setVisibility(View.VISIBLE);
			svV.setVisibility(View.VISIBLE);
			llRight.setVisibility(View.VISIBLE); 
			bl=false;
		} else {
			surfOn.setVisibility(View.VISIBLE);
			svV.setVisibility(View.VISIBLE);
			svH1.setVisibility(View.GONE);
			svH2.setVisibility(View.GONE);
			// 隐藏其它控件
			surfHide1.setVisibility(View.GONE);
			surfHide2.setVisibility(View.GONE);
			llRight.setVisibility(View.GONE);
			bl=true;
		}
	}

	@Override
	/**
	 * Create初始化方法
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 去掉标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_live_lesson_play);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		WindowManager.LayoutParams attr = getWindow().getAttributes();
		attr.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
		getWindow().setAttributes(attr);
		ll1 = (LinearLayout) findViewById(R.id.ll1);
		ll2 = (LinearLayout) findViewById(R.id.ll2);
		ll3 = (LinearLayout) findViewById(R.id.ll3);
		llRight = (LinearLayout) findViewById(R.id.llright);
		sView1=(SurfaceView) findViewById(R.id.videoview);
		sView2=(SurfaceView) findViewById(R.id.videoview2);
		sView3=(SurfaceView) findViewById(R.id.videoview3);
		/**
		 * 加载视频
		 */
		iniplay();
		sView1.setOnClickListener(videoClickListener);
		sView2.setOnClickListener(videoClickListener2);
		sView3.setOnClickListener(videoClickListener3);
		sView1.setOnTouchListener(video1);
		sView2.setOnTouchListener(video2);
		sView3.setOnTouchListener(video3);
	}

	// 初始化播放
	private void iniplay() {
		// 加载显示控件
		pd = ProgressDialog.show(LiveLessonPlayActivity.this, "缓冲中", "正在努力加载中 ...",
				true, false);
		initData1();
		initData2();
		initData3();

	}
	/**
	 * 双击videoview1全屏
	 */
private OnTouchListener video1 = new OnTouchListener() {
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
        if(MotionEvent.ACTION_DOWN == event.getAction()){  
            count++;  
            if(count == 1){  
                firClick = System.currentTimeMillis();  
                  
            } else if (count == 2){  
                secClick = System.currentTimeMillis();  
                if(secClick - firClick < 1000){  
                    //双击事件  
                	switchScreen(ll3, ll2, ll1,sView3,sView2,sView1,llRight);
                }  
                count = 0;  
                firClick = 0;  
                secClick = 0;  
                  
            }  
        }  
		return true;
	}
};
/**
 * 双击videoview2全屏
 */
private OnTouchListener video2 = new OnTouchListener() {

@Override
public boolean onTouch(View v, MotionEvent event) {
    if(MotionEvent.ACTION_DOWN == event.getAction()){  
        count++;  
        if(count == 1){  
            firClick = System.currentTimeMillis();  
              
        } else if (count == 2){  
            secClick = System.currentTimeMillis();  
            if(secClick - firClick < 1000){  
                //双击事件  
            	switchScreen(ll1, ll3, ll2,sView1,sView3,sView2,ll1);
            }  
            count = 0;  
            firClick = 0;  
            secClick = 0;  
              
        }  
    }  
	return true;
}
};
/**
 * 双击videoview3全屏
 */
private OnTouchListener video3 = new OnTouchListener() {

@Override
public boolean onTouch(View v, MotionEvent event) {
    if(MotionEvent.ACTION_DOWN == event.getAction()){  
        count++;  
        if(count == 1){  
            firClick = System.currentTimeMillis();  
              
        } else if (count == 2){  
            secClick = System.currentTimeMillis();  
            if(secClick - firClick < 1000){  
                //双击事件  
            	switchScreen(ll1, ll2, ll3,sView1,sView2,sView3,ll1);
            }  
            count = 0;  
            firClick = 0;  
            secClick = 0;  
              
        }  
    }  
	return true;
}
};
	/**
	 * 点击videoview1全屏
	 */
	private OnClickListener videoClickListener = new OnClickListener() {
		public void onClick(View v) {
//			switchScreen(ll3, ll2, ll1,sView3,sView2,sView1,llRight);
		}
	};
	/**
	 * 点击videoview2全屏
	 */
	private OnClickListener videoClickListener2 = new OnClickListener() {
		public void onClick(View v) {
//			switchScreen(ll1, ll3, ll2,sView1,sView3,sView2,ll1);
		}
	};
	/**
	 * 点击videoview3全屏
	 */
	private OnClickListener videoClickListener3 = new OnClickListener() {
		public void onClick(View v) {
//			switchScreen(ll1, ll2, ll3,sView1,sView2,sView3,ll1);
		}
	};

	private void initData1() {
		mediaPlayer1 = new MediaPlayer();
		if (mediaPlayer1.isPlaying()) {
			mediaPlayer1.reset();
			mediaPlayer1.start();
		} else {

			// 地址1
			// playVideo("rtsp://218.204.223.237:554/live/1/0547424F573B085C/gsfp90ef4k0a6iap.sdp");
			playVideo("rtsp://192.168.1.236:554/0/888888:888888/main");
		}
	}

	private void initData2() {
		mediaPlayer2 = new MediaPlayer();
		if (mediaPlayer2.isPlaying()) {
			mediaPlayer2.reset();
			mediaPlayer2.start();
		} else {
			// 地址2
			// playVideo2("rtsp://218.204.223.237:554/live/1/66251FC11353191F/e7ooqwcfbqjoo80j.sdp");
			playVideo2("rtsp://218.204.223.237:554/live/1/0547424F573B085C/gsfp90ef4k0a6iap.sdp");
		}
	}

	private void initData3() {
		mediaPlayer3 = new MediaPlayer();
		if (mediaPlayer3.isPlaying()) {
			mediaPlayer3.reset();
			mediaPlayer3.start();
		} else {
			// 地址3
//			playVideo3("rtsp://218.204.223.237:554/live/1/66251FC11353191F/e7ooqwcfbqjoo80j.sdp");
			playVideo3("http://192.168.0.2:89/Broadcast/500/SubVideo1/index.m3u8");
			// playVideo3("rtsp://192.168.0.191:554/0/888888:888888/main");
		}

	}

	private void playVideo(final String url) {
		// mediaPlayer1.setOnErrorListener(new OnErrorListener() {
		// @Override
		// public boolean onError(MediaPlayer mp, int what, int extra) {
		// // 加载显示控件
		// pd = ProgressDialog.show(FullscreenActivity.this, "缓冲中",
		// "正在努力加载中 ...", true, true);
		// return false;
		// }
		// });
		mediaPlayer1.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				pd1 = true;
				if (pd1 == true & pd2 == true & pd3 == true) {
					// 关掉加载圈
					pd.cancel();
					pd.dismiss();
				}
				mediaPlayer1.start();
			}
		});
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					mediaPlayer1.setDisplay(sView1.getHolder());
					mediaPlayer1.setDataSource(url);
					mediaPlayer1.prepare();
					setVolumeControlStream(AudioManager.STREAM_MUSIC);
				} catch (Exception e) { // /在这里增加播放失败.
					mediaPlayer1.release();
					if (mediaPlayer1 != null)
						e.printStackTrace();
				}
			}
		}, 100);
	}

	private void playVideo2(final String url) {
		// mediaPlayer2.setOnErrorListener(new OnErrorListener() {
		//
		// @Override
		// public boolean onError(MediaPlayer mp, int what, int extra) {
		// // 加载显示控件
		// pd = ProgressDialog.show(FullscreenActivity.this, "缓冲中",
		// "正在努力加载中 ...", true, true);
		// return false;
		// }
		// });
		mediaPlayer2.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				pd2 = true;
				if (pd1 == true & pd2 == true & pd3 == true) {
					// 关掉加载圈
					pd.cancel();
					pd.dismiss();
				}
				mediaPlayer2.start();
			}
		});

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					mediaPlayer2.setVolume(0, 0);
					mediaPlayer2.setDisplay(sView2.getHolder());
					mediaPlayer2.setDataSource(url);
					mediaPlayer2.prepare();
					setVolumeControlStream(AudioManager.STREAM_MUSIC);
				} catch (Exception e) { // /在这里增加播放失败.
					mediaPlayer2.release();
					if (mediaPlayer2 != null)
						e.printStackTrace();
				}
			}
		}, 130);
	}

	private void playVideo3(final String url) {
		// mediaPlayer3.setOnErrorListener(new OnErrorListener() {
		//
		// @Override
		// public boolean onError(MediaPlayer mp, int what, int extra) {
		// // 加载显示控件
		// pd = ProgressDialog.show(FullscreenActivity.this, "缓冲中",
		// "正在努力加载中 ...", true, true);
		// return false;
		// }
		// });
		mediaPlayer3.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				pd3 = true;
				if (pd1 == true & pd2 == true & pd3 == true) {
					// 关掉加载圈
					pd.cancel();
					pd.dismiss();
				}
				mediaPlayer3.start();

			}
		});
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					mediaPlayer3.setVolume(0, 0);
					mediaPlayer3.setDisplay(sView3.getHolder());
					mediaPlayer3.setDataSource(url);
					mediaPlayer3.prepare();
					setVolumeControlStream(AudioManager.STREAM_MUSIC);
				} catch (Exception e) { // /在这里增加播放失败.
					mediaPlayer3.release();
					if (mediaPlayer3 != null)
						e.printStackTrace();
				}
			}
		}, 160);
	}

	// app退出处理
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((event.getAction() == 0) && (keyCode == KeyEvent.KEYCODE_BACK)) {
			long currenttime = System.currentTimeMillis();
			if ((currenttime - lasttime) > 1000) {
				this.isExit = 0;
				lasttime = currenttime;
			}

			if (this.isExit == 0) {
				this.isExit++;
				PromptManager.showToast(this, "再点一次可退出");
				return true;
			}
			if (this.isExit == 1) {
				android.os.Process.killProcess(android.os.Process.myPid());
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}