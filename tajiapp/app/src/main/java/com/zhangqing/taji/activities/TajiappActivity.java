package com.zhangqing.taji.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.Response;
import com.zhangqing.taji.MyApplication;
import com.zhangqing.taji.R;
import com.zhangqing.taji.base.UserClass;
import com.zhangqing.taji.view.BottomBar;
import com.zhangqing.taji.view.BottomBar.OnTabClickListener;
import com.zhangqing.taji.view.TopBar;
import com.zhangqing.taji.view.TopBar.OnTopBarClickListener;

public class TajiappActivity extends Activity implements OnTabClickListener,
        OnTopBarClickListener {
    private Fragment[] fragments = new Fragment[7];
    private TopBar topBar;
    private int currentFragment = -1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != UserClass.Request_Main) {
            return;
        }
        if (resultCode == RESULT_OK) {
            //TODO 登录成功，刷新首页热门
        }
    }


    /**
     * 设置状态栏背景状态
     */
    private void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        SystemStatusManager tintManager = new SystemStatusManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.bgcolor_systembar);//状态栏无背景
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView(R.layout.activity_main);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            fragments[i] = initNewFragment(i);
            if (fragments[i] != null) {
                ft.add(R.id.contentframe, fragments[i]);
            }
        }
        ft.commit();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        bottomBar.setOnTabClickListener(this);
        topBar = (TopBar) findViewById(R.id.topbar);
        topBar.setOnTopBarClickListener(this);
        showFragment(0);

        MyApplication.getUser().isLogin(new Response.Listener<String>() {

            @Override
            public void onResponse(String arg0) {
                // TODO Auto-generated method stub

//                Intent intent = new Intent(TajiappActivity.this,
//                        RegisterFirstActivity.class);
//                startActivityForResult(intent, UserClass.Request_Main);

            }

        }, TajiappActivity.this);
    }

    private void showFragment(int whichFragment) {
        if (currentFragment == whichFragment) return;
        currentFragment = whichFragment;
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            if (i == currentFragment) {
                if (fragments[i] == null) {
                    fragments[i] = initNewFragment(whichFragment);
                    if (fragments[i] != null) {
                        ft.add(R.id.contentframe, fragments[i]);
                    }
                } else {
                    ft.show(fragments[i]);
                }

            } else {
                if (fragments[i] != null) {
                    ft.hide(fragments[i]);

                }

            }


        }
        ft.commit();

    }

    private Fragment initNewFragment(int whichFragment) {
        switch (whichFragment) {
            case 0:
                return new FragmentHomeHot();
            case 3:
                return new FragmentCircle();
            case 5:
                return new FragmentMy();


        }
        return null;
    }

    @Override
    public void tabSwitchTo(int whichParent) {
        topBar.switchToParent(TajiappActivity.this, whichParent);
    }

    @Override
    public void tabClickPublishBtn() {
        Intent intent = new Intent(TajiappActivity.this, PublishActivity.class);

        startActivity(intent);
        overridePendingTransition(R.anim.activity_open_bottom_in, 0);
    }


    @Override
    public void topbarSwitchToFragment(int whichFragment) {
        showFragment(whichFragment);
    }

    @Override
    public void topbarP1SearchClick() {
        // TODO Auto-generated method stub

    }
    // ----------------------------------------
}