package com.sky.maskpage.api;

import android.content.Context;
import android.view.View;

import com.sky.maskpage.adapter.MaskAdapter;
import com.sky.maskpage.entity.MaskInfo;

public interface OnMaskLoader {
    void displayMask(Context context, MaskAdapter adapter, View view, MaskInfo info, int position);
}
