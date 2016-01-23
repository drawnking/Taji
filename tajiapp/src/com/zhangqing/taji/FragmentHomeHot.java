package com.zhangqing.taji;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentHomeHot extends Fragment {
	ViewPager viewPager;
	

	List<View> viewList;
	List<String> titleList;
	
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragement_home, container, false);
		viewPager = (ViewPager) v.findViewById(R.id.viewpager);
		
		if (viewPager == null)
			Log.e("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "1");
		LayoutInflater flater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view1 = flater.inflate(R.layout.home_hot_page, null);
		View view2 = flater.inflate(R.layout.home_hot_page, null);
		View view3 = flater.inflate(R.layout.home_hot_page, null);
		Log.e("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "2");
		viewList = new ArrayList<View>();
		titleList = new ArrayList<String>();

		viewList.add(view1);
		viewList.add(view2);
		viewList.add(view3);
		Log.e("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				viewList.size() + "rr" + view1.toString());
		titleList.add("1111");
		titleList.add("2222");
		titleList.add("333");
		PagerAdapter adpater = new MyPagerAdapter(viewList, titleList);
		Log.e("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa", adpater.toString());
		viewPager.setAdapter(adpater);


		return v;
	}
	
	
	

}

class MyPagerAdapter extends PagerAdapter {
	private List<View> views;
	private List<String> titles;

	public MyPagerAdapter(List<View> views, List<String> titles) {
		super();
		this.views = views;
		this.titles = titles;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		Log.e("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "1" + views.size());
		return views.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(views.get(position));
		return views.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(views.get(position));
	}

	

}