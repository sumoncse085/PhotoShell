package com.teckfiesta.photoshell;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SplashActivity extends Activity {

	RelativeLayout re_fullpage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash1);
		getActionBar().hide();
		re_fullpage=(RelativeLayout) findViewById(R.id.re_fullpage);
		Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
       	@Override
			public void run() {
       		callIntent();
				}
		},1500);
		
	}
	public void callIntent(){
		re_fullpage.setBackgroundResource(R.drawable.page2);
		Handler handler=new Handler();
		 handler.postDelayed(new Runnable() {
		       	@Override
					public void run() {
		       		Intent intent=new Intent(SplashActivity.this, PhotoShellHomeActivity.class);
		       		startActivity(intent);
		       		finish();
						}
				},1500);
	}
}
