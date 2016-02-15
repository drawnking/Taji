package com.zhangqing.taji.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhangqing.taji.R;
import com.zhangqing.taji.activities.PublishActivity;

public class TopBar extends LinearLayout implements OnClickListener {


    //parent=0 首页上方
    private TextView textViewHomeHot;
    private TextView textViewHomeConcern;
    private TextView textViewHomeCamp;
    private ImageView imgViewHomeSearch;

    //parent=1 圈子上方
    private TextView textViewCircleCreate;
    private TextView textViewCircleTitle;
    private ImageView imgViewCircleSearch;

    //parent=2发布技能上方

    //parent=3 消息上方

    //parent=4,我的 上方

    private int currentParent = -1;
    private int currentChild = -1;
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
        switchToParent(context, 0);
    }


    public void switchToParent(Context context, int whichParent) {
        if (currentParent == whichParent)
            return;
        currentParent = whichParent;
        removeAllViews();
        setBackgroundResource(R.drawable.home_hot_title_bar_bg);
        //setBackgroundResource(R.color.bgcolor_bar_first);
        switch (whichParent) {
            case 0:
                if (textViewHomeHot == null) {
                    textViewHomeHot = new TextView(context);
                    textViewHomeHot.setText("热门");
                    textViewHomeHot.setTextSize(20);
                    textViewHomeHot.setGravity(Gravity.CENTER);
                    setMyTag(textViewHomeHot, "fragment", "0", "switch to another frament");

                    textViewHomeHot.setOnClickListener(this);
                }
                if (textViewHomeConcern == null) {
                    textViewHomeConcern = new TextView(context);
                    textViewHomeConcern.setText("关注");
                    textViewHomeConcern.setTextSize(20);
                    textViewHomeConcern.setGravity(Gravity.CENTER);
                    setMyTag(textViewHomeConcern, "fragment", "1", "switch to another frament");
                    textViewHomeConcern.setOnClickListener(this);
                }
                if (textViewHomeCamp == null) {
                    textViewHomeCamp = new TextView(context);
                    textViewHomeCamp.setText("活动");
                    textViewHomeCamp.setTextSize(20);
                    textViewHomeCamp.setGravity(Gravity.CENTER);
                    setMyTag(textViewHomeCamp, "fragment", "2", "switch to another frament");
                    textViewHomeCamp.setOnClickListener(this);
                }
                if (imgViewHomeSearch == null) {
                    imgViewHomeSearch = new ImageView(context);
                    imgViewHomeSearch
                            .setImageResource(R.drawable.icon_tab_search_normal);
                    setMyTag(imgViewHomeSearch, "action", "0", "首页搜索按钮");
                    imgViewHomeSearch.setOnClickListener(this);
                }
                LayoutParams la = getMyLayoutParams(0, LayoutParams.FILL_PARENT, 1,
                        0, 0);
                addView(textViewHomeHot, la);
                addView(textViewHomeConcern, la);
                addView(textViewHomeCamp, la);
                addView(imgViewHomeSearch,
                        getMyLayoutParams(LayoutParams.WRAP_CONTENT,
                                LayoutParams.WRAP_CONTENT, 0, 20, 20));
                switchToFragment(context, 0);
                break;
            case 1:
                if (textViewCircleCreate == null) {
                    textViewCircleCreate = new TextView(context);
                    textViewCircleCreate.setText("创建");
                    textViewCircleCreate.setTextSize(14);
                    textViewCircleCreate.setTextColor(getResources().getColor(R.color.textcolor_bar_first_unselect));
                    setMyTag(textViewCircleCreate, "action", "1", "圈子创建按钮");
                    textViewCircleCreate.setGravity(Gravity.CENTER);
                    textViewCircleCreate.setOnClickListener(this);
                }
                if (textViewCircleTitle == null) {
                    textViewCircleTitle = new TextView(context);
                    textViewCircleTitle.setText("圈子");
                    textViewCircleTitle.setTextColor(getResources().getColor(R.color.textcolor_bar_first_unselect));
                    textViewCircleTitle.setTextSize(20);
                    textViewCircleTitle.setGravity(Gravity.CENTER);

                }

                if (imgViewCircleSearch == null) {
                    imgViewCircleSearch = new ImageView(context);
                    imgViewCircleSearch
                            .setImageResource(R.drawable.icon_tab_search_normal);
                    setMyTag(imgViewCircleSearch, "action", "2", "圈子搜索按钮");
                    imgViewCircleSearch.setOnClickListener(this);
                }

                addView(textViewCircleCreate, getMyLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT, 0,
                        0, 0, 30, 10));
                addView(textViewCircleTitle, getMyLayoutParams(0, LayoutParams.FILL_PARENT, 1,
                        0, 0));

                addView(imgViewCircleSearch,
                        getMyLayoutParams(LayoutParams.WRAP_CONTENT,
                                LayoutParams.WRAP_CONTENT, 0, 20, 20));

                switchToFragment(context, 3);
                break;
            case 2:
                switchToFragment(context, 4);
                break;
            case 3:
                switchToFragment(context, 5);
                break;

            default:
                break;
        }

    }

    private void setMyTag(View v, String type, String which, String describe) {
        v.setTag(R.id.tagkey_type, type);
        v.setTag(R.id.tagkey_which, which);
        v.setTag(R.id.tagkey_describe, describe);
    }

    private LinearLayout.LayoutParams getMyLayoutParams(int width, int height,
                                                        int weight, int marginTop, int marginBottom) {
        LinearLayout.LayoutParams layoutParams = new LayoutParams(width,
                height, weight);
        layoutParams.setMargins(0, marginTop, 0, marginBottom);
        layoutParams.gravity = Gravity.CENTER;
        return layoutParams;
    }

    private LinearLayout.LayoutParams getMyLayoutParams(int width, int height,
                                                        int weight, int marginTop, int marginBottom, int marginLeft, int marginRight) {
        LinearLayout.LayoutParams layoutParams = new LayoutParams(width,
                height, weight);
        layoutParams.setMargins(marginLeft, marginTop, marginRight, marginBottom);
        layoutParams.gravity = Gravity.CENTER;
        return layoutParams;
    }

    private void switchToFragment(Context context, int whichFragment) {
        if (whichFragment < 3) {
            switchToChild(context, whichFragment);
        }
        if (topBarClickListener != null)
            topBarClickListener.topbarSwitchToFragment(whichFragment);
    }

    private void switchToChild(Context context, int whichChild) {
        if (currentParent != 0) return;
        currentChild = whichChild;
        int unselectTextColor = context.getResources().getColor(
                R.color.textcolor_bar_first_unselect);
        int selectTextColor = context.getResources().getColor(
                R.color.textcolor_bar_first_select);

        textViewHomeHot.setTextColor(unselectTextColor);
        textViewHomeCamp.setTextColor(unselectTextColor);
        textViewHomeConcern.setTextColor(unselectTextColor);
        switch (whichChild) {
            case 0:
                textViewHomeHot.setTextColor(selectTextColor);
                break;
            case 1:
                textViewHomeConcern.setTextColor(selectTextColor);
                break;
            case 2:
                textViewHomeCamp.setTextColor(selectTextColor);
                break;
            default:
                break;
        }

    }

    public interface OnTopBarClickListener {
        public void topbarSwitchToFragment(int whichFragment);

        public void topbarP1SearchClick();


    }

    public void setOnTopBarClickListener(OnTopBarClickListener tc) {
        this.topBarClickListener = tc;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub


        String type = (String) v.getTag(R.id.tagkey_type);
        if (type.equals("fragment")) {
            String whichFragment = (String) v.getTag(R.id.tagkey_which);
            switchToFragment(context, Integer.valueOf(whichFragment).intValue());
            Log.e("TopbarSwitchToFragment", "" + Integer.valueOf(whichFragment).intValue());
        } else if (type.equals("action")) {


        }


//        switch ((Integer) v.getTag()) {
//            case 0:
//                switchToChild(context, 0);
//                if (topBarClickListener != null)
//                    topBarClickListener.topbarP1HotClick();
//                break;
//            case 1:
//                switchToChild(context, 1);
//                if (topBarClickListener != null)
//                    topBarClickListener.topbarP1ConcernClick();
//                break;
//            case 2:
//                switchToChild(context, 2);
//                if (topBarClickListener != null)
//                    topBarClickListener.topbarP1CampClick();
//                break;
//            case 3:
//                if (topBarClickListener != null)
//                    topBarClickListener.topbarP1SearchClick();
//                break;
//
//        }
    }

}
