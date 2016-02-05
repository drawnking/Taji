package com.zhangqing.taji.activities;

import net.sf.json.JSONObject;
import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.zhangqing.taji.MyApplication;
import com.zhangqing.taji.R;
import com.zhangqing.taji.base.VolleyInterface;

public class RegisterThirdActivity extends Activity {
	private TextView passwordTextView;
	private String mobile;
	private String smsCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_third);

		TextView tv = (TextView) findViewById(R.id.register_xieyi);
		tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

		passwordTextView = (TextView) findViewById(R.id.register_password);

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			mobile = bundle.getString("mobile");
			smsCode = bundle.getString("smsCode");
		}
	}

	public void onClickButtonClearPassword(View v) {
		passwordTextView.setText("");
	}

	public void onClickButtonSubmitPassword(View v) {
		MyApplication.getUser().doRegisterDone(RegisterThirdActivity.this,
				mobile, passwordTextView.getText().toString(), smsCode,
				new VolleyInterface(RegisterThirdActivity.this) {

					@Override
					public void onMySuccess(JSONObject jsonObject) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(),
								jsonObject.getString("success"),
								Toast.LENGTH_LONG).show();
						finish();
					}

					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub

					}
				});
	}
}
