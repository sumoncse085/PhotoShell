package com.teckfiesta.photoshell;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CompleteActivity extends Activity {

	RelativeLayout re_fullpage;
	TextView tv_text;
	Button btn_go;
	
	//boolean btn_go
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.complete);
		getActionBar().hide();
		String text = "<font color=#7030A0>The</font><br />" +
				"<font color=#ffffff>George Washington Studios Summer Collection 2013 </font><br />"+
				"<font color=#7030A0>Has been successfully delivered to your device!</font>";
		tv_text=(TextView) findViewById(R.id.tv_text);
		tv_text.setText(Html.fromHtml(text));
		Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
       	@Override
			public void run() {
       		callanothertextview();
				}
		},1500);
        btn_go=(Button) findViewById(R.id.btn_go);
        
		
	}
	public void callanothertextview(){
		String text = "<font color=#7030A0>New PhotoShell content has been delivered to the following location:</font><br />" +
				"<font color=#ffffff>/SD/PhotoShell</font>";
		tv_text.setText(Html.fromHtml(text));
		Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
       	@Override
			public void run() {
       		callanothertextview2();
				}
		},1500);
	}
	
	public void callanothertextview2(){
		String text = "<font color=#7030A0>We hope you enjoy the content of this PhotoShell!</font><br /><br />" +
				"<font color=#ffffff>For more information about PhotoShell</font><br /><br />"+
				"<font color=#ffffff>Please visit</font><br />"+
				"<font color=#ffffff>www.sparkania.com</font>"
				;
		tv_text.setText(Html.fromHtml(text));
		btn_go.setVisibility(View.VISIBLE);
		
	}
	public void finishh(View view){
		finish();
	}


}
