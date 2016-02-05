package com.zhangqing.taji.base;

import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.zhangqing.taji.MyApplication;
import com.zhangqing.taji.util.Md5Util;

public class UserClass {
	private final String URLHEAD = "http://taji.whutech.com";
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

	public String getAreaId() {
		return areaId;
	}

	private ErrorListener getToastError(final Context context, final String msg) {
		return new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(context, msg, 2000).show();
			}
		};
	}

	public String getUserId() {
		return userId;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void isLogin(Listener<String> listener, Context context) {

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
				"www.baidu.com", listener, getToastError(context, "网络异常"));
		MyApplication.getRequestQeuee().add(stringRequest);

		return;
	}

	public void saveSharedPreference() {
		Editor editor = sharedPreferences.edit();
		editor.putString("user_id", this.userId);
		editor.putString("verify_code", this.verifyCode);
		editor.putString("area_id", this.areaId);
		editor.commit();
	}

	public void doRegisterSmsSend(Context context, String mobile,
			VolleyInterface vif) {
		VolleyRequest.RequestGet(context, URLHEAD + "/sms.php?phone=" + mobile,
				"doRegisterSmsSend", vif);

	}

	public void doRegisterSmsCheck(Context context, String mobile,
			String smsCode, VolleyInterface vif) {
		VolleyRequest.RequestGet(context, URLHEAD + "/sms_verify?phone="
				+ mobile + "&code=" + smsCode, "doRegisterSmsSend", vif);
	}

	public void doRegisterDone(Context context, String mobile, String password,
			String smsCode, VolleyInterface vif) {
		VolleyRequest.RequestGet(context, URLHEAD + "/register?phone=" + mobile
				+ "&code=" + smsCode + "&password=" + Md5Util.getMd5(password),
				"doRegisterSmsSend", vif);
	}
	/*
	 * 
	 * http://taji.whutech.com/sms.php?phone=17072750175
	 * http://taji.whutech.com/sms_verify?phone=15207131254&code=8674
	 * http://taji.whutech.com/register?phone=15207131254&code=&password=xxx
	 * 
	 */

	// public void setAreaId(String areaId) {
	// this.areaId = areaId;
	// }
	//
	// public void setUserId(String userId) {
	// this.userId = userId;
	// }
	//
	// public void setVerifyCode(String verifyCode) {
	// this.verifyCode = verifyCode;
	// }

}
