package com.zhangqing.taji.activities;

import net.sf.json.JSONObject;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.zhangqing.taji.MyApplication;
import com.zhangqing.taji.R;
import com.zhangqing.taji.base.VolleyInterface;

public class RegisterFirstActivity extends Activity {
	private EditText mobileEditText;
	private String mobile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_first);

		mobileEditText = (EditText) findViewById(R.id.register_mobile);

	}

	public void onClickButtonClearEdittext(View v) {
		mobileEditText.setText("");
	}

	public void onClickButtonSubmitMobile(View v) {
		mobile = mobileEditText.getText().toString();
		if (mobile.length() != 11) {
			Toast.makeText(getApplicationContext(), "手机号格式错误",
					Toast.LENGTH_LONG).show();
			return;
		}
		final Context context_ = getApplicationContext();

		final Dialog dialog_ = new Dialog(this, R.style.LodingDialog);
		dialog_.setContentView(R.layout.layout_progressbar);
		dialog_.setCancelable(false);
		dialog_.show();

		MyApplication.getUser().doRegisterSmsSend(context_, mobile,
				new VolleyInterface(context_) {

					@Override
					public void onMySuccess(JSONObject jsonObject) {

						dialog_.dismiss();


						Intent intent = new Intent(context_,
								RegisterSecondActivity.class);
						Bundle b = new Bundle();
						b.putString("mobile", mobile);
						intent.putExtras(b);
						startActivity(intent);
						finish();
					}

					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub
						dialog_.dismiss();
					}

				});

		return;
		// StringRequest stringRequest = new StringRequest(
		// "http://taji.whutech.com/sms.php?phone="+mobile,
		// new Listener<String>() {
		//
		// @Override
		// public void onResponse(String arg0) {
		// // TODO Auto-generated method stub
		// Intent intent=new Intent(context_,RegisterSecondActivity.class);
		// Bundle b=new Bundle();
		// b.putString("mobile", mobile);
		// intent.putExtras(b);
		// startActivity(intent);
		// finish();
		// }
		// }, new Response.ErrorListener() {
		//
		// @Override
		// public void onErrorResponse(VolleyError arg0) {
		// // TODO Auto-generated method stub
		// Toast.makeText(context_, "网络异常",
		// Toast.LENGTH_LONG).show();
		// //((Button)findViewById(R.id.register_sendsms)).setEnabled(true);
		// }
		// });
		//
		// MyApplication.getRequestQeuee().add(stringRequest);

		// MyApplication.getRequestQeuee().start();
		// ((Button)findViewById(R.id.register_sendsms)).setEnabled(false);
	}
}
