package com.hxc.supreme.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxc.supreme.R;

/**
 * created by huxc  on 2017/12/12.
 * func：    自定义view Fragment
 * email: hxc242313@qq.com
 */

public class AddressFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        return view;
    }
}
