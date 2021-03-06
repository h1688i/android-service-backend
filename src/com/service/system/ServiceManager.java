package com.service.system;

import com.service.backend.SystemBroadcastReceiver;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;

/**
 * 背景服務管理
 */

public class ServiceManager {
	/**
	 * 動態註冊系統廣播監聽
	 * @param context
	 */
	static public void startSystemBroadcastReceiver(String tag,Context context,Class<?> serviceClass)
	{
		if (!Device.isServiceRunning(tag,context,serviceClass))
		{
			IntentFilter filter = new IntentFilter();
			context.registerReceiver(new SystemBroadcastReceiver(), filter);
		}
	}
	/**
	 * 啟動背景服務,如果該服務尚未執行則執行服務,
	 * 如果服務已執行則不做任何動作
	 * 
	 * @param tag log顯示用
	 * @param context
	 * @param serviceClass 要啟動的服務
	 * @param action 要做的動作
	 */
	static public void start(String tag,Context context, Class<?> serviceClass,String action) {
		Intent intent = new Intent(context,serviceClass);
		intent.setAction(action);
		if (!Device.isServiceRunning(tag,context,serviceClass))
			context.startService(intent);
	}
	
	/**
	 * 停止背景服務,如果該服務尚未執行則不做任何動作,
	 * 如果服務執行中則停止服務
	 * 
	 * @param tag log顯示用
	 * @param context
	 * @param serviceClass 要啟動的服務
	 */
	static public void stop(String tag,Context context, Class<?> serviceClass) {
		Intent intent = new Intent(context,serviceClass);
		if (Device.isServiceRunning(tag,context,serviceClass))
			context.stopService(intent);
	}
	
	/**
	 * 設定前台不顯示

	 * @param service 要設定的服務
	 */
	
	static public void setForegroundDoNotShow(Service service)
	{
		if (VERSION.SDK_INT < 18) {  
			short PID = 0;
			service.startForeground(PID, null);  
        } 
	}
}
