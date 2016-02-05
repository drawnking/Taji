package com.zhangqing.taji;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.zhangqing.taji.base.UserClass;

public class MyApplication extends Application {
	private static RequestQueue requestQueue;
	private static UserClass user;
	


	
	public static RequestQueue getRequestQeuee() {
		return requestQueue;
	}
	public static UserClass getUser() {
		return user;
	}



	


	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		requestQueue=Volley.newRequestQueue(getApplicationContext());
		user=new UserClass(getSharedPreferences("taji", MODE_PRIVATE));
		
	}






	
}
