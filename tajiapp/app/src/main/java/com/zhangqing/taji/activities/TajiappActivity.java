package com.zhangqing.taji.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Response;
import com.zhangqing.taji.R;
import com.zhangqing.taji.R.id;
import com.zhangqing.taji.R.layout;
import com.zhangqing.taji.base.UserClass;
import com.zhangqing.taji.view.BottomBar;
import com.zhangqing.taji.view.TopBar;
import com.zhangqing.taji.view.BottomBar.OnTabClickListener;
import com.zhangqing.taji.view.TopBar.OnTopBarClickListener;

public class TajiappActivity extends Activity implements OnTabClickListener,
		OnTopBarClickListener {
	private SharedPreferences sharedPreferences;
	private UserClass user;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		sharedPreferences = getSharedPreferences("taji", MODE_PRIVATE);

		BottomBar bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
		bottomBar.setOnTabClickListener(this);
		TopBar topBar = (TopBar) findViewById(R.id.topbar);
		topBar.setOnTopBarClickListener(this);
		showFragment(1);

		user = new UserClass(sharedPreferences);
		user.isLogin(new Response.Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				// TODO Auto-generated method stub

//				 Intent intent = new Intent(TajiappActivity.this,
//				 RegisterFirstActivity.class);
//				 startActivity(intent);

			}

		}, TajiappActivity.this);
	}

	private void showFragment(int which) {
		getFragmentManager().beginTransaction()
				.replace(R.id.contentframe, new FragmentHomeHot(), "1")
				.commit();

	}

	// --------------------------------------------------
	@Override
	public void tabHomeClick() {
		Toast.makeText(this, "11", Toast.LENGTH_SHORT).show();

	}

	@Override
	public void tabCircleClick() {

	}

	@Override
	public void tabCenterClick() {

	}

	@Override
	public void tabMessageClick() {

	}

	@Override
	public void tabMyClick() {

	}

	// ----------------------------------------------

	@Override
	public void topbarP1HotClick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void topbarP1ConcernClick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void topbarP1CampClick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void topbarP1SearchClick() {
		// TODO Auto-generated method stub

	}
	// ----------------------------------------
}