package com.sky.maskpage.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

import com.sky.maskpage.R;
import com.sky.maskpage.adapter.MaskAdapter;
import com.sky.maskpage.api.OnMackListener;
import com.sky.maskpage.api.OnMaskLoader;
import com.sky.maskpage.entity.MaskInfo;

import java.util.ArrayList;
import java.util.List;

public class MaskPageView extends RelativeLayout {

    private Context mContext;
    private ViewPager maskVp;
    private OnMaskLoader loader;
    private OnMackListener listener;
    private boolean isRuning = false;
    private int totalCount;
    private CountDownTimer timer;
    private int currentCount;
    private long waitTime = 5000;

    public MaskPageView(Context context) {
        this(context, null);
    }

    public MaskPageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaskPageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        inflate(context, R.layout.layout_mask_page, this);
        maskVp = findViewById(R.id.mask_vp);
    }

    public void build(int[] resIds, List<View> highViews) {
        if (resIds == null || highViews == null || resIds.length == 0 || highViews.size() == 0 || resIds.length != highViews.size() || isRuning)
            return;
        isRuning = true;
        currentCount = 0;
        totalCount = resIds.length;
        List<PageInfo> infos = new ArrayList<>();
        for (int i = 0; i < totalCount; i++) {
            View view = LayoutInflater.from(mContext).inflate(resIds[i], null);
            MaskInfo info;
            View highView = highViews.get(i);
            if (highView != null) {
                final int[] locations = new int[2];
                highView.getLocationInWindow(locations);
                int width = highView.getWidth();
                int height = highView.getHeight();
                info = new MaskInfo(locations, width, height);
            } else {
                info = new MaskInfo(false);
            }
            infos.add(new PageInfo(view, info));
        }
        maskVp.setAdapter(new MaskAdapter(mContext, loader, listener, infos));
        timer = new CountDownTimer(totalCount * waitTime, waitTime) {

            @Override
            public void onTick(long millisUntilFinished) {
                maskVp.setCurrentItem(currentCount, false);
                currentCount++;
            }

            @Override
            public void onFinish() {
                currentCount = 0;
                isRuning = false;
                if (listener != null) {
                    listener.onFinish();
                }
            }
        };
        timer.start();
    }

    public void onDestroy() {
        if (timer != null)
            timer.cancel();
        currentCount = 0;
        isRuning = false;
    }

    public MaskPageView setMaskLoader(OnMaskLoader loader) {
        this.loader = loader;
        return this;
    }

    public MaskPageView setMackListener(OnMackListener listener) {
        this.listener = listener;
        return this;
    }

    public MaskPageView setWaitTime(long waitTime) {
        this.waitTime = waitTime;
        return this;
    }

    public class PageInfo {
        private View layout;
        private MaskInfo info;

        public PageInfo(View layout, MaskInfo info) {
            this.layout = layout;
            this.info = info;
        }

        public View getLayout() {
            return layout;
        }

        public MaskInfo getInfo() {
            return info;
        }
    }
}
