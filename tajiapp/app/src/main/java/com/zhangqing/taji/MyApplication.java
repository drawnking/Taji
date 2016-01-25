package com.zhangqing.taji;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class MyApplication extends Application {
	public static RequestQueue requestQueue;

	public static RequestQueue getRequestQeuee() {
		return requestQueue;
	}
	


	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		requestQueue=Volley.newRequestQueue(getApplicationContext());
	}
}
