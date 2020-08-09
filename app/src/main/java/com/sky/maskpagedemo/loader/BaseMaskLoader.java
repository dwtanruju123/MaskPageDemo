package com.sky.maskpagedemo.loader;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.sky.maskpage.adapter.MaskAdapter;
import com.sky.maskpage.api.OnMaskLoader;
import com.sky.maskpage.entity.MaskInfo;
import com.sky.maskpage.util.StatusUtil;
import com.sky.maskpagedemo.R;

public class BaseMaskLoader implements OnMaskLoader {

    @Override
    public void displayMask(Context context, final MaskAdapter adapter, View view, MaskInfo info, final int position) {
        View target = view.findViewById(R.id.target_view);
        View hint = view.findViewById(R.id.tv_hint);
        if (!info.exist) return;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) target.getLayoutParams();
        params.width = info.getWidth();
        params.height = info.getHeight();
        target.setLayoutParams(params);
        int[] locations = info.getLocations();
        target.setX(locations[0]);
        target.setY(locations[1] - StatusUtil.getStatusBarHeight(context));
        if (hint != null) {
            hint.setX(locations[0]);
            hint.setY(locations[1] - StatusUtil.getStatusBarHeight(context) + info.getHeight());
        }
        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.OnClick(v, position);
            }
        });
    }
}
