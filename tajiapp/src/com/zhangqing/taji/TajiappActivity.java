package com.zhangqing.taji;

import com.zhangqing.taji.BottomBar.OnTabClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class TajiappActivity extends Activity implements OnTabClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		BottomBar bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
		bottomBar.setOnTabClickListener(this);

		showFragment(1);
	}

	private void showFragment(int which){
		getFragmentManager().beginTransaction().replace(R.id.contentframe, new FragmentHomeHot(), "1").commit();
		
	}

	// --------------------------------------------------
	@Override
	public void tabHomeClick() {
		Toast.makeText(this, "11", 2000).show();

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
}