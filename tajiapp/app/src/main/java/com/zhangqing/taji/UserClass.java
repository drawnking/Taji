package com.zhangqing.taji;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

public class UserClass {
	private SharedPreferences sharedPreferences;
	private String userId;
	private String verifyCode;
	private String areaId;

	public UserClass(SharedPreferences sharedPreferences) {
		this.sharedPreferences = sharedPreferences;
		this.userId = sharedPreferences.getString("user_id", "");
		this.verifyCode = sharedPreferences.getString("verify_code", "");
		this.areaId = sharedPreferences.getString("area_id", "");
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public void saveSharedPreference() {
		Editor editor = sharedPreferences.edit();
		editor.putString("user_id", this.userId);
		editor.putString("verify_code", this.verifyCode);
		editor.putString("area_id", this.areaId);
		editor.commit();
	}

	public void isLogin(Listener<String> listener,Context context) {

		// TODO Auto-generated method stub
		if (userId.equals("")) {
			listener.onResponse("no data");
			return;
		}
		if (verifyCode.equals("")) {
			listener.onResponse("no data");
			return;
		}

		StringRequest stringRequest = new StringRequest(Method.GET,
				"www.baidu.com", listener,getToastError(context,"网络异常"));
		MyApplication.getRequestQeuee().add(stringRequest);

		return;
	}
	
	private ErrorListener getToastError(final Context context,final String msg){
		return new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
			}
		};
	}
	

}
