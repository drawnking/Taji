package com.zhangqing.taji.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhangqing.taji.R;
import com.zhangqing.taji.R.drawable;
import com.zhangqing.taji.R.id;
import com.zhangqing.taji.R.layout;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class FragmentHomeHotViewThen extends LinearLayout {

	private SwipeRefreshLayout swipeRefreshLayout;
	private Context context;
	private View containerView;

	public FragmentHomeHotViewThen(Context context) {
		super(context);
		this.context = context;
		// TODO Auto-generated constructor stub
		LayoutInflater flater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		containerView = flater.inflate(R.layout.home_hot_page_then, null);
		
		ListView listView = (ListView) containerView
				.findViewById(R.id.home_hot_page_listview_then);

		//

		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int j = 0; j < 4; j++) {
			Map<String, Object> listitem = new HashMap<String, Object>();
			listitem.put("pic", R.drawable.icon_tab_home_hot_loading);
			listItems.add(listitem);
		}
		listView.setAdapter(new SimpleAdapter(context, listItems,
				R.layout.home_hot_page_first_gridview_item, new String[] { "pic" },
				new int[] { R.id.home_hot_page_gridview_imgview }));

		swipeRefreshLayout = (SwipeRefreshLayout) containerView
				.findViewById(R.id.id_swipe_ly_then);
		swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_light,
				android.R.color.holo_red_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_green_light);
		swipeRefreshLayout
				.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

					@Override
					public void onRefresh() {
						// tv.setText("正在刷新");
						// // TODO Auto-generated method stub
						new Handler().postDelayed(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub

								swipeRefreshLayout.setRefreshing(false);
							}
						}, 3000);
					}
				});

		

		addView(containerView);

	}

}


