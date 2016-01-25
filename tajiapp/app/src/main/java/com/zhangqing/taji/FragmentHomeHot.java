package com.zhangqing.taji;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentHomeHot extends Fragment implements OnClickListener {
	ViewPager viewPager;
	HorizontalScrollView scrollView;

	List<View> viewList;

	int currenCategory = 0;
	List<Category> categoryList;
	List<TextView> textViewList = new ArrayList<TextView>();
	private SwipeRefreshLayout swipeRefreshLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragement_home, container, false);
		viewPager = (ViewPager) v.findViewById(R.id.viewpager);
		scrollView = (HorizontalScrollView) v
				.findViewById(R.id.horizon_scrollview_category);
		
		
		
		
		
		
		LinearLayout categoryLayout = (LinearLayout) v
				.findViewById(R.id.category_title_linearlayout);

		categoryList = getCategoryList();

		// init linearLayout
		for (int i = 0; i < categoryList.size(); i++) {
			TextView tempTextView = new TextView(getActivity());
			tempTextView.setTag(categoryList.get(i));
			tempTextView.setText(categoryList.get(i).getCategoryName());
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.FILL_PARENT);
			// params.setMargins(20, 0, 20, 0);
			if (i == currenCategory) {
				tempTextView.setBackgroundResource(R.drawable.bar_type);
				tempTextView.setTextColor(getActivity().getResources()
						.getColor(R.color.textcolor_bar_second_select));

			} else {
				tempTextView.setBackgroundResource(0);
				tempTextView.setTextColor(getActivity().getResources()
						.getColor(R.color.textcolor_bar_second_unselect));
			}
			tempTextView.setTextSize(16);

			tempTextView.setGravity(Gravity.CENTER);

			tempTextView.setOnClickListener(this);
			textViewList.add(tempTextView);
			categoryLayout.addView(tempTextView, params);

		}

		// LayoutInflater flater = (LayoutInflater) getActivity()
		// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		viewList = new ArrayList<View>();

		for (int i = 0; i < categoryList.size(); i++) {
			View view1 = inflater.inflate(R.layout.home_hot_page_first, null);
			ViewPager viewPagerInside = (ViewPager) view1
					.findViewById(R.id.viewpagerinside);
			GridView gridView=(GridView) view1.findViewById(R.id.home_hot_page_gridview);
			
			
			swipeRefreshLayout=(SwipeRefreshLayout) view1.findViewById(R.id.id_swipe_ly);
			swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
	        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
	            
	            @Override
	            public void onRefresh() {
//	                tv.setText("正在刷新");
//	                // TODO Auto-generated method stub
	                new Handler().postDelayed(new Runnable() {
	                    
	                    @Override
	                    public void run() {
	                        // TODO Auto-generated method stub
	                        
	                        swipeRefreshLayout.setRefreshing(false);
	                    }
	                }, 6000);
	            }
	        });
	        
	        
			List<View> viewsInside = new ArrayList<View>();
			ImageView imgLoading1 = new ImageView(getActivity());
			imgLoading1.setImageResource(R.drawable.loading);
			ImageView imgLoading2 = new ImageView(getActivity());
			imgLoading2.setImageResource(R.drawable.loading);
			ImageView imgLoading3 = new ImageView(getActivity());
			imgLoading3.setImageResource(R.drawable.loading);
			imgLoading1.setScaleType(ScaleType.FIT_XY);
			viewsInside.add(imgLoading1);
			viewsInside.add(imgLoading2);
			viewsInside.add(imgLoading3);
			viewPagerInside.setAdapter(new MyPagerAdapter(viewsInside));
			
			
			
			Log.e("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "2");
			view1.setTag(categoryList.get(i));
			viewList.add(view1);

		}
		PagerAdapter adpater = new MyPagerAdapter(viewList);
		Log.e("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa", adpater.toString());
		viewPager.setAdapter(adpater);

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				currenCategory = arg0;
				Toast.makeText(getActivity(), arg0 + "", 2000).show();
				updateCategoryView();
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		updateCategoryView();
		return v;
	}

	private List<Category> getCategoryList() {
		List<Category> categoryList = new ArrayList<Category>();
		categoryList.add(new Category(0, "2", "广场2"));
		categoryList.add(new Category(1, "3", "广场3"));
		categoryList.add(new Category(2, "4", "广场4"));
		categoryList.add(new Category(3, "5", "广场5"));
		categoryList.add(new Category(4, "6", "广场6"));
		categoryList.add(new Category(5, "7", "广场7"));
		categoryList.add(new Category(6, "8", "广场8"));
		categoryList.add(new Category(7, "9", "广场9"));
		categoryList.add(new Category(8, "10", "广场19"));
		categoryList.add(new Category(9, "11", "广场29"));
		categoryList.add(new Category(10, "12", "广场39"));
		return categoryList;

	}

	private void updateCategoryView() {

		for (int i = 0; i < textViewList.size(); i++) {

			if (i == currenCategory) {
				textViewList.get(i).setBackgroundResource(R.drawable.bar_type);
				textViewList.get(i).setTextColor(
						getActivity().getResources().getColor(
								R.color.textcolor_bar_second_select));

			} else {
				textViewList.get(i).setBackgroundResource(0);
				textViewList.get(i).setTextColor(
						getActivity().getResources().getColor(
								R.color.textcolor_bar_second_unselect));
			}
			textViewList.get(i).setPadding(20, 15, 20, 15);
		}

		int textViewLeft = textViewList.get(currenCategory).getLeft();
		int textViewWidth = textViewList.get(currenCategory).getWidth() + 20;
		if (textViewLeft - scrollView.getScrollX() < 0) {
			scrollView.smoothScrollTo(textViewLeft - 10, 0);
		} else if (textViewLeft + textViewWidth > scrollView.getScrollX()
				+ scrollView.getMeasuredWidth()) {
			scrollView.smoothScrollTo(
					textViewLeft + textViewWidth
							- scrollView.getMeasuredWidth(), 0);
		}

		// Toast.makeText(getActivity(), textViewLeft +
		// "q"+scrollView.getScrollX()+"a"+scrollView.getWidth()+"b"+textViewWidth,
		// 1000).show();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getTag().equals(categoryList.get(currenCategory)))
			return;
		currenCategory = ((Category) v.getTag()).getCategoryNum();
		viewPager.setCurrentItem(currenCategory, true);

		// updateCategoryView();

	}

}

class MyPagerAdapter extends PagerAdapter {
	private List<View> views;

	public MyPagerAdapter(List<View> views) {
		super();
		this.views = views;

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

class AdsType {
	private int adsNum;
	private int adsId;
	private String adsUrl;
	private String adsClickUrl;

	public int getAdsNum() {
		return adsNum;
	}

	public void setAdsNum(int adsNum) {
		this.adsNum = adsNum;
	}

	public int getAdsId() {
		return adsId;
	}

	public void setAdsId(int adsId) {
		this.adsId = adsId;
	}

	public String getAdsUrl() {
		return adsUrl;
	}

	public void setAdsUrl(String adsUrl) {
		this.adsUrl = adsUrl;
	}

	public String getAdsClickUrl() {
		return adsClickUrl;
	}

	public void setAdsClickUrl(String adsClickUrl) {
		this.adsClickUrl = adsClickUrl;
	}

	public AdsType(int adsNum, String adsUrl) {
		this(adsNum, 0, adsUrl, "");
	}

	public AdsType(int adsNum, int adsId, String adsUrl, String adsClickUrl) {
		super();
		this.adsNum = adsNum;
		this.adsId = adsId;
		this.adsUrl = adsUrl;
		this.adsClickUrl = adsClickUrl;
	}

}

class Category {
	private int categoryNum;
	private String categoryId;
	private String categoryName;

	public int getCategoryNum() {
		return categoryNum;
	}

	public void setCategoryNum(int categoryNum) {
		this.categoryNum = categoryNum;
	}

	public Category(int categoryNum, String categoryId, String categoryName) {
		super();
		this.categoryNum = categoryNum;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}