package com.zhangqing.taji.base;

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
    public static final int Request_Main = 101;
    public static final int Request_Register_First = 102;
    public static final int Request_Register_Second = 103;
    public static final int Request_Register_Third = 104;

    private final String URLHEAD = "http://taji.whutech.com";
    private SharedPreferences sharedPreferences;
    private String userId;
    private String openId;
    private String areaId;
    private String mobile;

    public UserClass(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.userId = sharedPreferences.getString("user_id", "");
        this.openId = sharedPreferences.getString("open_id", "");
        this.areaId = sharedPreferences.getString("area_id", "");
        this.mobile = sharedPreferences.getString("mobile", "");
    }


    private ErrorListener getToastError(final Context context, final String msg) {
        return new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        };
    }

    public String getUserId() {
        return userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void isLogin(Listener<String> listener, Context context) {

        // TODO Auto-generated method stub
        if (userId.equals("")) {
            listener.onResponse("no data");
            return;
        }
        if (openId.equals("")) {
            listener.onResponse("no data");
            return;
        }

        StringRequest stringRequest = new StringRequest(Method.GET,
                "http://www.baidu.com", listener, getToastError(context, "网络异常"));
        MyApplication.getRequestQeuee().add(stringRequest);

        return;
    }

    public void saveSharedPreference(String userId, String openId,String mobile) {
        this.userId = userId;
        this.openId = openId;
        this.mobile=mobile;
        Editor editor = sharedPreferences.edit();
        editor.putString("user_id", this.userId);
        editor.putString("open_id", this.openId);
        editor.putString("mobile", this.mobile);
        editor.commit();
    }

    public void doRegisterSmsSend(String mobile,
                                  VolleyInterface vif) {
        VolleyRequest.RequestGet(URLHEAD + "/sms.php?phone=" + mobile,
                "doRegisterSmsSend", vif);

    }

    public void doRegisterSmsCheck(String mobile,
                                   String smsCode, VolleyInterface vif) {
        VolleyRequest.RequestGet(URLHEAD + "/sms_verify?phone="
                + mobile + "&code=" + smsCode, "doRegisterSmsCheck", vif);
    }

    public void doRegisterDone(String mobile, String password,
                               String smsCode, VolleyInterface vif) {
        VolleyRequest.RequestGet(URLHEAD + "/register?phone=" + mobile
                        + "&code=" + smsCode + "&password=" + Md5Util.getMd5(password),
                "doRegisterDone", vif);
    }

    public void getSkillListAll(VolleyInterface vif) {
        VolleyRequest.RequestGet(URLHEAD + "/skill", "getSkillListAll", vif);
    }

	/* 17091647364
     * {"status":"200","id":"1005","open_id":"a945852413f99f52bc6f2dabf969da8f"}
	 * http://taji.whutech.com/sms.php?phone=17072750175
	 * http://taji.whutech.com/sms_verify?phone=15207131254&code=8674
	 * http://taji.whutech.com/register?phone=15207131254&code=&password=xxx
	 * http://taji.whutech.com/login?phone=&password=
	 * 获取技能标签的接口：http://taji.whutech.com/skill
	 * 用户增加或更新兴趣和技能标签的接口：http://taji.whutech.com/intadskill?user=用户ID&interest=兴趣1.兴趣2.兴趣3....&skill=技能1.技能2.技能3...
	 * 用户关注大咖接口http://taji.whutech.com/follow?user=用户ID&follow=大咖ID
	 */


}
