package com.service.system;

import com.file.io.IO;
import com.file.io.User;
import com.service.backend.CoreService;
import com.service.backend.PushService;
import com.service.backend.SystemBroadcastReceiver;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	private String TAG = MainActivity.class.getSimpleName();
	public static MainActivity context = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		context = this;
		setContentView(R.layout.activity_main);
		startBackendService(null);
	}
	
	@Override
	public void onBackPressed() {
		kill();
		return;
	}
	
	/**
	 * 啟動背景服務
	 * 
	 * @param userId 如果不使用socket通訊則傳入null即可
	 */
	public void startBackendService(String userId)
	{
		User.saveId(userId,this);
		ServiceManager.start(TAG,this, CoreService.class,CoreService.INTENT_ACTION_START);
		ServiceManager.start(TAG,this, PushService.class,PushService.INTENT_ACTION_START);
		ServiceManager.startSystemBroadcastReceiver(TAG,this,SystemBroadcastReceiver.class);
	}
	
	private void kill()
	{
		AlertDialog dialog = new AlertDialog.Builder(this)
		.setTitle("Notify")
		.setMessage("Are you sure you want to exit from Temperature app?")
		.setNegativeButton("Yes", new DialogInterface.OnClickListener()
		{
			@SuppressLint("NewApi")
			public void onClick(DialogInterface dialog, int which) {
				finish();
				 //android.os.Process.killProcess(android.os.Process.myPid());
			}
		})
		.setPositiveButton("Cancel", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{	
				
			}
		}).show();
		
		int alertTitle = getResources().getIdentifier("alertTitle", "id", "android");
		TextView textView = (TextView) dialog.findViewById(alertTitle);
		textView.setTextSize(20);
	}
}
