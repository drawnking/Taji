package com.zhangqing.taji.activities;

import net.sf.json.JSONObject;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.zhangqing.taji.MyApplication;
import com.zhangqing.taji.R;
import com.zhangqing.taji.base.VolleyInterface;

public class RegisterSecondActivity extends Activity {
	private String mobile;
	private Button btnReSend;
	private EditText editSmsCode;
	private String smsCode;

	private Runnable runnable;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.arg1 != 0) {
				btnReSend.setText("重新获取(" + msg.arg1 + "s)");
			} else {
				btnReSend.setText("重新获取");
				btnReSend.setEnabled(true);
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_second);
		btnReSend = (Button) findViewById(R.id.btn_send_again);
		editSmsCode = (EditText) findViewById(R.id.register_smsverifycode);

		mobile = getIntent().getExtras().getString("mobile");
		((TextView) findViewById(R.id.temp_register_mobileshow))
				.setText("您的手机号：" + mobile);

		runnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 30; i >= 0; i--) {

					Message msg = new Message();
					msg.arg1 = i;
					handler.sendMessage(msg);

					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}
				}
			}
		};

		new Thread(runnable).start();

	}

	public void onClickBtnReSms(View v) {
		btnReSend.setEnabled(false);

		final Context context_ = getApplicationContext();

		MyApplication.getUser().doRegisterSmsSend(context_, mobile,
				new VolleyInterface(context_) {

					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub
						btnReSend.setEnabled(true);
					}

					@Override
					public void onMySuccess(JSONObject jsonObject) {
						// TODO Auto-generated method stub
						if (!jsonObject.get("status").equals("200")) {
							performErrorListener();
							return;
						}
						new Thread(runnable).start();
					}

				});

	}

	public void onClickBtnCheckSmsverify(View v) {
		smsCode = editSmsCode.getText().toString();
		MyApplication.getUser().doRegisterSmsCheck(getApplicationContext(),
				mobile, smsCode, new VolleyInterface(getApplicationContext()) {
					@Override
					public void onMySuccess(JSONObject jsonObject) {
						// TODO Auto-generated method stub
						if (!jsonObject.getString("status").equals("200")) {
							Toast.makeText(getApplicationContext(),
									jsonObject.getString("error"),
									Toast.LENGTH_SHORT).show();
							return;
						}
						
						Intent intent=new Intent(RegisterSecondActivity.this, RegisterThirdActivity.class);
						Bundle bundle=new Bundle();
						bundle.putString("mobile", mobile);
						bundle.putString("smsCode", smsCode);
						intent.putExtras(bundle);
						startActivity(intent);
						finish();
						Toast.makeText(getApplicationContext(),
								"校验成功",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub

					}
				});

	}
}
