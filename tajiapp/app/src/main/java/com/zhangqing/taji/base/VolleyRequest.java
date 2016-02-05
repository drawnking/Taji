package com.zhangqing.taji.base;

import android.content.Context;

import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;
import com.zhangqing.taji.MyApplication;

public class VolleyRequest {
	public static StringRequest stringRequest;
	public static Context context;
	public static void RequestGet(Context mContext,String url,String tag,VolleyInterface vif){
		MyApplication.getRequestQeuee().cancelAll(tag);
		stringRequest=new StringRequest(Method.GET,url, vif.loadingListener(), vif.errorListener());
		stringRequest.setTag(tag);
		MyApplication.getRequestQeuee().add(stringRequest);
		MyApplication.getRequestQeuee().start();
	}

}
