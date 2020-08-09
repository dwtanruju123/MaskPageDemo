package com.sky.maskpage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.sky.maskpage.api.OnMackListener;
import com.sky.maskpage.api.OnMaskLoader;
import com.sky.maskpage.entity.MaskInfo;
import com.sky.maskpage.view.MaskPageView;

import java.util.List;

public class MaskAdapter extends PagerAdapter {
    private Context mContext;
    private OnMaskLoader loader;
    private OnMackListener listener;
    private List<MaskPageView.PageInfo> lists;

    public MaskAdapter(Context mContext, OnMaskLoader loader, OnMackListener listener, List<MaskPageView.PageInfo> lists) {
        this.mContext = mContext;
        this.loader = loader;
        this.listener = listener;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists == null ? 0 : lists.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View layout = lists.get(position).getLayout();
        container.addView(layout);
        MaskInfo info = lists.get(position).getInfo();
        if (loader != null) {
            loader.displayMask(mContext, this, layout, info, position);
        }
        return layout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        (container).removeView((View) object);
    }

    public void OnClick(View v, int position) {
        if (listener != null)
            listener.OnClick(v, position);
    }
}
