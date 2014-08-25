package com.teckfiesta.photoshell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.MultiAutoCompleteTextView.CommaTokenizer;
import android.widget.RelativeLayout;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PhotoShellHomeActivity extends Activity {

	RelativeLayout re_fullpage;
	TextView tv_text;
	CheckBox ck_accept;
	Button btn_go;
	String folderlocation;
	ProgressDialog pd;
	int filenum=1;
	int maxnumimage=30;
	//boolean btn_go
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		folderlocation=Environment.getExternalStorageDirectory() + "/PhotoShell";
		createFolder();
		ck_accept=(CheckBox) findViewById(R.id.ck_accept);
		btn_go=(Button) findViewById(R.id.btn_go);
		btn_go.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(ck_accept.isChecked()){
					btn_go.setBackgroundResource(R.drawable.buttonpresses);
					pd=ProgressDialog.show(PhotoShellHomeActivity.this, "PhotoShell",
							"Please wait", true);
					new  AsyncTaskLoadImage().execute();
					
				}
				else{
					new AlertDialog.Builder(PhotoShellHomeActivity.this)
				    .setTitle("PhotoShell")
				    .setMessage("Please accept the terms")
				    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int which) { 
				            // continue with delete
				        	dialog.cancel();
				        }
				     }) .show();
				}
			}
		});
		
		getActionBar().hide();

		String text = "<font color=#7030A0>The content of this</font>" +
				"<font color=#7030A0>PhotoShell is the property of:</font><br />" +
				"<font color=#ffffff>George Washington Studios</font><br />"+
				"<font color=#7030A0>And is intended for your </font>" +
				"<font color=#7030A0>Personal Use Only</font><br /><br />" +
				"<font color=#7030A0>Sale or Redistribution of this </font>" +
				"<font color=#7030A0>without the owners consent</font>" +
				"<font color=#7030A0>without the owners consent</font>" +
				"<font color=#7030A0>is prohibited.</font><br /><br />" +
				"<font color=#7030A0>For more information</font>" +
				"<font color=#7030A0>contact:</font><br />" +
				"<font color=#ffffff>G.Washington</font>"+
				"<font color=#ffffff>@ABCstudio.com</font>";
				tv_text=(TextView) findViewById(R.id.tv_text);
				tv_text.setText(Html.fromHtml(text));
	}
	
	
	public static Bitmap getBitmapFromAsset(Context context, String filePath) {
	    AssetManager assetManager = context.getAssets();

	    InputStream istr;
	    Bitmap bitmap = null;
	    try {
	        istr = assetManager.open(filePath);
	        bitmap = BitmapFactory.decodeStream(istr);
	    } catch (IOException e) {
	        // handle exception
	    }

	    return bitmap;
	}
	
	
	private class AsyncTaskLoadImage extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {
			boolean result=false;
			
			try {
				
				String filename = "Slide"+filenum;
				//int id = getResources().getIdentifier(filename, "drawable", getPackageName());
				filename=filename+".JPG";
				Bitmap bm = getBitmapFromAsset( PhotoShellHomeActivity.this,filename);
//				File file = new File(filePath);
//				if(file.exists())      
//				//Do somehting
//				else
//				// Do something else.
				
				
				File file = new File(folderlocation, filename);
				Log.e("a "+filenum, ""+file.length()+file.exists()+"  "+bm);
				

				if(file.exists())  {
					FileOutputStream outStream = new FileOutputStream(file);
					bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
					outStream.flush();
					outStream.close();
				}
				else{
					
					FileOutputStream outStream = new FileOutputStream(file);
					bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
					outStream.flush();
					outStream.close();
				}
				
				result=true;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			return result;
			
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);

			if (result){
				if(filenum>=maxnumimage){
					if(pd!=null){
						if(pd.isShowing()){
							pd.dismiss();
						}
					}
					Intent intent=new Intent(PhotoShellHomeActivity.this, CompleteActivity.class);
					startActivity(intent);
					finish();
				}
				else{
					filenum++;
					new  AsyncTaskLoadImage().execute();
				}
				
			}
			else{
				if(pd.isShowing()){
					pd.dismiss();
				}
				filenum=1;
				new AlertDialog.Builder(PhotoShellHomeActivity.this)
			    .setTitle("PhotoShell")
			    .setMessage("Failed to save image. Please try again")
			    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // continue with delete
			        	dialog.cancel();
			        }
			     }) .show();
			}

		}

	}

	
	public void createFolder(){
		  File folder = new File(folderlocation);
		    boolean success = true;
		    if (!folder.exists()) {
		        //Toast.makeText(MainActivity.this, "Directory Does Not Exist, Create It", Toast.LENGTH_SHORT).show();
		        success = folder.mkdir();
		    }
	}

}
