package com.zhangqing.taji.activities;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.zhangqing.taji.MyApplication;
import com.zhangqing.taji.R;
import com.zhangqing.taji.base.VolleyInterface;

import net.sf.json.JSONObject;

public class RegisterThirdActivity extends Activity {
    private TextView passwordTextView;
    private Button btnRegister;
    private String mobile;
    private String smsCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_third);

        TextView tv = (TextView) findViewById(R.id.register_third_xieyi_tv);
        btnRegister= (Button) findViewById(R.id.register_third_done_btn);
        tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        passwordTextView = (TextView) findViewById(R.id.register_third_password_edttxt);

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
        btnRegister.setEnabled(false);
        MyApplication.getUser().doRegisterDone(mobile, passwordTextView.getText().toString(), smsCode,
                new VolleyInterface(RegisterThirdActivity.this) {

                    @Override
                    public void onMySuccess(JSONObject jsonObject) {
                        btnRegister.setEnabled(true);

                        String userId = "";
                        String openId = "";
                        if (jsonObject.has("id")) userId = jsonObject.getString("id");
                        if (jsonObject.has("user_id")) userId = jsonObject.getString("user_id");
                        if (jsonObject.has("open_id")) openId = jsonObject.getString("open_id");


                        if (userId.equals("") || openId.equals("")) {
                            Toast.makeText(getApplicationContext(),
                                    "注册失败",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }

                        MyApplication.getUser().saveSharedPreference(userId, openId,mobile);
                        setResult(RESULT_OK);
                        finish();
                    }

                    @Override
                    public void onMyError(VolleyError error) {
                        btnRegister.setEnabled(true);
                    }
                });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }


}
