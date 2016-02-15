package com.zhangqing.taji.base;

import net.sf.json.JSONObject;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import android.content.Context;
import android.widget.Toast;

public abstract class VolleyInterface {
	private Context context;
	private Listener<String> listener;
	private ErrorListener errorListener;

	public VolleyInterface(Context context) {
		this.context = context;
	}

	public abstract void onMySuccess(JSONObject jsonObject);

	public abstract void onMyError(VolleyError error);

	public Listener<String> loadingListener() {
		listener = new Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				// TODO Auto-generated method stub

				JSONObject jsonObject;
				try {
					jsonObject = JSONObject.fromString(arg0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					onMyError(new VolleyError("网络解析异常"));
					Toast.makeText(context, "网络解析异常", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if (!(jsonObject.getString("status").equals("200"))) {
					onMyError(new VolleyError(jsonObject.getString("status")
							+ "错误"));
					Toast.makeText(context, jsonObject.getString("msg"),
							Toast.LENGTH_SHORT).show();
					return;
				}

				onMySuccess(jsonObject);

			}
		};
		return listener;
	}

	public ErrorListener errorListener() {
		errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				onMyError(arg0);

				Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
			}
		};
		return errorListener;
	}

	public void performErrorListener(String exceptionMessage) {
		errorListener.onErrorResponse(new VolleyError(exceptionMessage));
	}

	public void performErrorListener() {
		errorListener.onErrorResponse(null);
	}
}
