package com.zhangqing.taji.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.zhangqing.taji.R;
import com.zhangqing.taji.view.MyEditText;
import com.zhangqing.taji.view.ResizeLayout;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/10.
 */
public class PublishActivity extends Activity implements View.OnClickListener{
    private static final int BIGGER = 1;
    private static final int SMALLER = 2;
    private static final int MSG_RESIZE = 1;

    private static final int HEIGHT_THREADHOLD = 30;

    private ScrollView scrollView;

    private LinearLayout parentViewEdittext;
    private LinearLayout parentViewFaceGrid;
    private LinearLayout parentViewPublishBtn;

    private ViewPager viewPagerFace;
    private EditText editText;

    private List<ImageView> pointList;


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_RESIZE: {
                    if (msg.arg1 == BIGGER) {
                        // findViewById(R.id.publish_parentview_cover).setVisibility(View.VISIBLE);
                        parentViewPublishBtn.setVisibility(View.VISIBLE);
                        //parentViewFaceGrid.setVisibility(View.VISIBLE);
                    } else {
                        // findViewById(R.id.publish_parentview_cover).setVisibility(View.GONE);
                        parentViewPublishBtn.setVisibility(View.GONE);

                        hideFaceGrid();

                        scrollView.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("top2", parentViewEdittext.getTop() + "|" + parentViewEdittext.getBottom() + "|" + scrollView.getHeight());
                                scrollView.smoothScrollTo(0, parentViewEdittext.getBottom() - scrollView.getHeight());
                            }
                        });
                    }
                }
                break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private TranslateAnimation mShowAction;
    private TranslateAnimation mHiddenAction;

    public void disableSoftInputMethod(EditText ed) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        int currentVersion = android.os.Build.VERSION.SDK_INT;
        String methodName = null;
        if (currentVersion >= 16) {
            // 4.2
            methodName = "setShowSoftInputOnFocus";
        } else if (currentVersion >= 14) {
            // 4.0
            methodName = "setSoftInputShownOnFocus";
        }

        if (methodName == null) {
            ed.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus;
            try {
                setShowSoftInputOnFocus = cls.getMethod(methodName, boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(ed, false);
            } catch (NoSuchMethodException e) {
                ed.setInputType(InputType.TYPE_NULL);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.activity_publish);

        scrollView = (ScrollView) findViewById(R.id.publish_scrollview);
        viewPagerFace = (ViewPager) findViewById(R.id.publish_viewpager);
        editText = (EditText) findViewById(R.id.publish_edittext);

        disableSoftInputMethod(editText);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.e("onFocusChange", "z" + hasFocus);
                onClickEditText(editText);
            }
        });

        parentViewEdittext = (LinearLayout) findViewById(R.id.publish_parentview_edittext);
        parentViewFaceGrid = (LinearLayout) findViewById(R.id.publish_parentview_facegrid);
        parentViewPublishBtn = (LinearLayout) findViewById(R.id.publish_parentview_publish_btn);


        pointList = new ArrayList<ImageView>();
        ImageView iv1 = (ImageView) findViewById(R.id.publish_face_point1);
        pointList.add(iv1);
        ImageView iv2 = (ImageView) findViewById(R.id.publish_face_point2);
        pointList.add(iv2);
        ImageView iv3 = (ImageView) findViewById(R.id.publish_face_point3);
        pointList.add(iv3);


        ResizeLayout resizeLayout = (ResizeLayout) findViewById(R.id.publish_rootview);
        resizeLayout.setOnResizeListener(new ResizeLayout.OnResizeListener() {
            @Override
            public void OnResize(int w, int h, int oldw, int oldh) {
                int change = BIGGER;
                if (h < oldh) {
                    change = SMALLER;
                }

                Log.e("setOnResizeListener", "" + change);

                Message msg = new Message();
                msg.what = 1;
                msg.arg1 = change;
                mHandler.sendMessage(msg);
            }
        });
        viewPagerFace.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < pointList.size(); i++) {
                    if (i == position) {
                        pointList.get(i).setImageResource(R.drawable.icon_viewpager_point_selected);
                    } else {
                        pointList.get(i).setImageResource(R.drawable.icon_viewpager_point_unselected);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        MyFacePagerAdapter myFacePagerAdapter=new MyFacePagerAdapter(this);
        //myFacePagerAdapter.setOnClickListener(this);
        viewPagerFace.setAdapter(myFacePagerAdapter);

    }


    @Override
    public void finish() {
        // TODO Auto-generated method stub
        super.finish();
        //关闭窗体动画显示
        this.overridePendingTransition(0, R.anim.activity_open_bottom_out);
    }

    public void onClickBtnFinish(View v) {
        finish();
    }

    public void onClickBtnHiddenFaceGrid(View v) {
        hideFaceGrid();
        //  InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//        editText.requestFocus();
    }

    public void onClickBtnShowFaceGrid(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        showFaceGrid();
    }

    public void onClickBtnShowImm(View v) {
        hideFaceGrid();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void onClickEditText(View v) {
        Log.e("onClickEditText", "a");

        if (parentViewFaceGrid.getVisibility() == View.GONE) {
            Log.e("onClickEditText", "requestFocus");

            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, 0);
        } else {
            Log.e("onClickEditText", "notFocus");

        }
    }

    private void showFaceGrid() {
        mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);
        parentViewFaceGrid.setAnimation(mShowAction);
        parentViewFaceGrid.setVisibility(View.VISIBLE);
    }

    private void hideFaceGrid() {
        mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f);
        mHiddenAction.setDuration(500);
        parentViewFaceGrid.setAnimation(mHiddenAction);
        parentViewFaceGrid.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        //Log.e("onClickFace",v.getTag(R.id.tagkey_which)+"|");
        ((MyEditText)editText).insertDrawable((String) v.getTag(R.id.tagkey_which));
        Log.e("onClickFace", editText.getText().toString());
    }


    class MyFacePagerAdapter extends PagerAdapter {
        private final int ROW_NUM = 3;
        private final int COLUMN_NUM = 7;
        List<View> mviewList;
        Context mContext;

        View.OnClickListener clickListener = null;


        public MyFacePagerAdapter(View.OnClickListener listener) {
            this.mContext = PublishActivity.this;
            this.clickListener=listener;

            mviewList = new ArrayList<View>();


            int i = 1;

            while (i <= 56) {
                LinearLayout rootLayout = new LinearLayout(mContext);
                rootLayout.setOrientation(LinearLayout.VERTICAL);

                for (int row = 0; row < ROW_NUM; row++) {
                    LinearLayout rowLayout = new LinearLayout(mContext);
                    rowLayout.setOrientation(LinearLayout.HORIZONTAL);
                    outer:
                    for (int column = 0; column < COLUMN_NUM; column++) {
                        if (column == COLUMN_NUM - 1 && row == ROW_NUM - 1) {
                            ImageView iv = new ImageView(mContext);
                            iv.setScaleType(ImageView.ScaleType.CENTER);
                            iv.setImageResource(R.drawable.icon_register_first_inputcha);
                            rowLayout.addView(iv, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));


                        } else {

                            int resourceId = R.drawable.ic_launcher;
                            try {
                                Field field = R.drawable.class.getDeclaredField("emoji_" + i);
                                resourceId = Integer.parseInt(field.get(null).toString());
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            ImageView iv = new ImageView(mContext);
                            iv.setScaleType(ImageView.ScaleType.CENTER);
                            iv.setImageResource(resourceId);
                            iv.setTag(R.id.tagkey_which,i+"");
                            iv.setOnClickListener(clickListener);

                            rowLayout.addView(iv, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
                            i++;
                        }

                    }

                    rootLayout.addView(rowLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));

                }
                mviewList.add(rootLayout);


            }


        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mviewList.get(position), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return mviewList.get(position);
        }

        @Override
        public int getCount() {
            return mviewList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mviewList.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
