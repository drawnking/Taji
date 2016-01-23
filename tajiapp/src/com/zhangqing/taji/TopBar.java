package com.zhangqing.taji;

import com.zhangqing.taji.BottomBar.OnTabClickListener;
import com.zhangqing.taji.TopBar.OnTopBarClickListener;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TopBar extends LinearLayout implements OnClickListener {
	private TextView textViewHot;
	private TextView textViewConcern;
	private TextView textViewCamp;
	private ImageView imgViewSearch;

	private int currentPosition = -1;
	private OnTopBarClickListener topBarClickListener;

	private Context context;

	public TopBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		// 取xml属性
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.mybar);
		int position = Integer.parseInt(typedArray
				.getString(R.styleable.mybar_topposition));
		switchToParent(context, position);
	}

	public void switchToParent(Context context, int which) {
		if (currentPosition == which)
			return;
		currentPosition = which;
		removeAllViews();
		setBackgroundResource(R.color.bgcolor_bar_first);
		switch (which) {
		case 0:
			if (textViewHot == null) {
				textViewHot = new TextView(context);
				textViewHot.setText("热门");
				textViewHot.setTextSize(20);
				textViewHot.setGravity(Gravity.CENTER);
				textViewHot.setTag(0);
				textViewHot.setOnClickListener(this);
			}
			if (textViewConcern == null) {
				textViewConcern = new TextView(context);
				textViewConcern.setText("关注");
				textViewConcern.setTextSize(20);

				textViewConcern.setGravity(Gravity.CENTER);
				textViewConcern.setTag(1);
				textViewConcern.setOnClickListener(this);
			}
			if (textViewCamp == null) {
				textViewCamp = new TextView(context);
				textViewCamp.setText("活动");
				textViewCamp.setTextSize(20);
				textViewCamp.setGravity(Gravity.CENTER);
				textViewCamp.setTag(2);
				textViewCamp.setOnClickListener(this);
			}
			if (imgViewSearch == null) {
				imgViewSearch = new ImageView(context);
				imgViewSearch
						.setImageResource(R.drawable.icon_tab_search_normal);
				imgViewSearch.setTag(3);
				imgViewSearch.setOnClickListener(this);
			}
			LayoutParams la = initLayoutParams(0, LayoutParams.FILL_PARENT, 1,
					0, 0);
			addView(textViewHot, la);
			addView(textViewConcern, la);
			addView(textViewCamp, la);
			addView(imgViewSearch,
					initLayoutParams(LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT, 0, 20, 20));
			switchToChild(context, 0);
			break;

		case 1:
			
			break;
		default:
			break;
		}

	}

	private LinearLayout.LayoutParams initLayoutParams(int width, int height,
			int weight, int marginTop, int marginBottom) {
		LinearLayout.LayoutParams layoutParams = new LayoutParams(width,
				height, weight);
		layoutParams.setMargins(0, marginTop, 0, marginBottom);
		layoutParams.gravity = Gravity.CENTER;
		return layoutParams;
	}

	public void switchToChild(Context context, int which) {
		int unselectTextColor = context.getResources().getColor(
				R.color.textcolor_bar_first_unselect);
		int selectTextColor = context.getResources().getColor(
				R.color.textcolor_bar_first_select);

		textViewHot.setTextColor(unselectTextColor);
		textViewCamp.setTextColor(unselectTextColor);
		textViewConcern.setTextColor(unselectTextColor);
		switch (which) {
		case 0:
			textViewHot.setTextColor(selectTextColor);
			break;
		case 1:
			textViewConcern.setTextColor(selectTextColor);
			break;
		case 2:
			textViewCamp.setTextColor(selectTextColor);
			break;
		default:
			break;
		}

	}

	public interface OnTopBarClickListener {
		public void topbarP1HotClick();

		public void topbarP1ConcernClick();

		public void topbarP1CampClick();

		public void topbarP1SearchClick();

		public void tabMyClick();

	}

	public void setOnTopBarClickListener(OnTopBarClickListener tc) {
		this.topBarClickListener = tc;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch ((Integer) v.getTag()) {
		case 0:
			switchToChild(context, 0);
			if (topBarClickListener != null)
				topBarClickListener.topbarP1HotClick();
			break;
		case 1:
			switchToChild(context, 1);
			if (topBarClickListener != null)
				topBarClickListener.topbarP1ConcernClick();
			break;
		case 2:
			switchToChild(context, 2);
			if (topBarClickListener != null)
				topBarClickListener.topbarP1CampClick();
			break;
		case 3:
			if (topBarClickListener != null)
				topBarClickListener.topbarP1SearchClick();
			break;

		}
	}

}
