package com.zhangqing.taji;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BottomBar extends LinearLayout implements OnClickListener {

	private int[] imgs = { R.drawable.icon_tab_home_normal,
			R.drawable.icon_tab_circle_normal,
			R.drawable.icon_tab_center_normal,
			R.drawable.icon_tab_message_normal, R.drawable.icon_tab_my_normal };
	private int[] imgs_down = { R.drawable.icon_tab_home_press,
			R.drawable.icon_tab_circle_press,
			R.drawable.icon_tab_center_normal,
			R.drawable.icon_tab_message_press, R.drawable.icon_tab_my_press };
	private String[] texts = { "首页", "圈子", "", "消息", "我的" };

	private TextView[] textViews = new TextView[5];
	private ImageViewWithPoint[] imageViews = new ImageViewWithPoint[5];
	private LinearLayout linearLayout;

	private int position;

	public int getPosition() {
		return position;
	}

	public Context context;
	private OnTabClickListener tabClickListener;

	public BottomBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;

		// 取xml属性
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.mybar);
		position = Integer.parseInt(typedArray
				.getString(R.styleable.mybar_bottomposition));

		// 外布局
		setBackgroundResource(R.color.bgcolor_bar_bottom);
		LayoutParams layoutParamsoutWrap = new LayoutParams(0,
				LayoutParams.WRAP_CONTENT, 1);
		LayoutParams layoutParamsoutFill = new LayoutParams(0,
				LayoutParams.FILL_PARENT, 1);
		layoutParamsoutWrap.setMargins(0, 10, 0, 10);

		for (int i = 0; i < imgs.length; i++) {
			linearLayout = new LinearLayout(context);
			textViews[i] = new TextView(context);
			imageViews[i] = new ImageViewWithPoint(context);

			// linearLayout.setBackgroundColor(res
			// .getColor(R.color.bgcolor_bar_bottom_center));

			linearLayout.setOrientation(LinearLayout.VERTICAL);

			textViews[i].setText(texts[i]);
			// textViews[i].setTextColor(R.color.textcolor_bar_first_unselect);
			// imageViews[i].setImageResource(imgs[i]);
			imageViews[i].setPadding(15, 0, 15, 0);
			textViews[i].setGravity(Gravity.CENTER);

			if (i == 2) {
				linearLayout.addView(
						imageViews[i],
						initLayoutParams(LayoutParams.FILL_PARENT,
								LayoutParams.FILL_PARENT, 0, 0));
				linearLayout
						.setBackgroundResource(R.color.bgcolor_bar_bottom_center);
				addView(linearLayout, layoutParamsoutFill);
			} else {
				linearLayout.addView(
						imageViews[i],
						initLayoutParams(LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT, 0, 0));
				linearLayout.addView(
						textViews[i],
						initLayoutParams(LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT, 0, 0));

				addView(linearLayout, layoutParamsoutWrap);
			}

			linearLayout.setTag(i);
			// linearLayout.setTag(1, i);
			linearLayout.setOnClickListener(this);
//			if(i==0){
//				linearLayout.performClick();
//			}

		}
		setWithPoint(1, true);
		setWithPoint(3, true);
		swichTo(position);

	}

	public void setWithPoint(int which, boolean withPoint) {
		imageViews[which].setWithPoint(withPoint);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// Toast.makeText(context, v.getTag().toString(), 2000).show();
		swichTo((Integer) v.getTag());
		if (tabClickListener != null) {
			switch ((Integer) v.getTag()) {
			case 0:
				tabClickListener.tabHomeClick();
				break;
			case 1:
				tabClickListener.tabCircleClick();
				break;
			case 2:
				tabClickListener.tabCenterClick();
				break;
			case 3:
				tabClickListener.tabMessageClick();
				break;
			case 4:
				tabClickListener.tabMyClick();
				break;
			default:
				break;
			}
		}
	}

	private LinearLayout.LayoutParams initLayoutParams(int width, int height,
			int marginTop, int marginBottom) {
		LinearLayout.LayoutParams layoutParams = new LayoutParams(width, height);
		layoutParams.setMargins(0, marginTop, 0, marginBottom);
		layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
		return layoutParams;
	}

	private void swichTo(int which) {
		position = which;
		for (int i = 0; i < imageViews.length; i++) {
			if (i == which) {
				imageViews[i].setImageResource(0);
				imageViews[i].setImageResource(imgs_down[i]);
				imageViews[i].setPointColor(R.color.textcolor_bar_first_select);
				textViews[i].setTextColor(context.getResources().getColor(
						R.color.textcolor_bar_first_select));
			} else {
				imageViews[i].setImageResource(0);
				imageViews[i].setImageResource(imgs[i]);
				imageViews[i].setPointColor(R.color.bgcolor_bar_bottom_center);
				textViews[i].setTextColor(context.getResources().getColor(
						R.color.textcolor_bar_first_unselect));
			}
		}
		//
		//
	}

	public interface OnTabClickListener {
		public void tabHomeClick();

		public void tabCircleClick();

		public void tabCenterClick();

		public void tabMessageClick();

		public void tabMyClick();

	}

	public void setOnTabClickListener(OnTabClickListener tc) {
		this.tabClickListener = tc;
	}

}

class ImageViewWithPoint extends ImageView {
	Paint paint = new Paint();
	Context context;
	boolean withPoint = false;
	int pointColor = R.color.bgcolor_bar_bottom_center;

	public void setPointColor(int pointColor) {
		this.pointColor = pointColor;
	}

	public void setWithPoint(boolean withPoint) {
		this.withPoint = withPoint;
	}

	public ImageViewWithPoint(Context context) {
		super(context);
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	public ImageViewWithPoint(Context context, boolean withPoint) {
		this(context);
		this.withPoint = withPoint;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if (withPoint == true) {
			paint.setColor(context.getResources().getColor(pointColor));
			paint.setAntiAlias(true);
			canvas.drawCircle(getWidth() - 10, 10, 10, paint);

			// Toast.makeText(context, getWidth()+"||"+getHeight(),
			// 2000).show();
		}

	}

}