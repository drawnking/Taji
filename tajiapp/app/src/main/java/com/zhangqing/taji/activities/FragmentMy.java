package com.zhangqing.taji.activities;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangqing.taji.R;

/**
 * Created by Administrator on 2016/2/14.
 */
public class FragmentMy extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my, container, false);

        return v;
    }
}
