package com.sky.maskpagedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sky.maskpage.api.OnMackListener;
import com.sky.maskpage.view.MaskPageView;
import com.sky.maskpagedemo.loader.BaseMaskLoader;
import com.sky.maskpagedemo.util.CustomDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int[] resIds = new int[]{R.layout.layout_guide_0, R.layout.layout_guide_1, R.layout.layout_guide_2, R.layout.layout_guide_3, R.layout.layout_guide_4, R.layout.layout_guide_5};
    private List<View> highViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.bt);
        RecyclerView rv = findViewById(R.id.rv);
        RelativeLayout item_1 = findViewById(R.id.item_1);
        final RelativeLayout item_2 = findViewById(R.id.item_2);
        RelativeLayout item_3 = findViewById(R.id.item_3);
        final RelativeLayout item_4 = findViewById(R.id.item_4);
        RelativeLayout item_5 = findViewById(R.id.item_5);
        highViews.add(item_1);
//        highViews.add(item_2);
        highViews.add(item_3);
//        highViews.add(item_4);
        highViews.add(item_5);
        ArrayList<String> data = new ArrayList<>();
        data.add("首页");
        data.add("测试");
        data.add("我的");
        data.add("设置");
        data.add("发现");
        data.add("聊天");
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(manager);
        rv.setAdapter(new MyAdapter(data));
        rv.post(new Runnable() {
            @Override
            public void run() {
                View view = manager.findViewByPosition(0);
                highViews.add(view);
                highViews.add(item_2);
                highViews.add(item_4);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_dialog, null);
                final MaskPageView mpv = view.findViewById(R.id.mpv);
                final CustomDialog dialog = new CustomDialog(MainActivity.this, view, Gravity.CENTER).setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mpv.onDestroy();
                    }
                }).setCanceledOnTouchOutside(false).setMax(true, true).build();
                mpv.setMackListener(new OnMackListener() {
                    @Override
                    public void OnClick(View v, int position) {
                        switch (v.getId()) {
                            case R.id.target_view:
                                dialog.dismiss();
                                break;
                        }
                    }

                    @Override
                    public void onFinish() {
                        dialog.dismiss();
                    }
                }).setMaskLoader(new BaseMaskLoader()).build(resIds, highViews);
                dialog.show();
            }
        });
    }

    public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

        private ArrayList<String> mList;

        public MyAdapter(ArrayList<String> list) {
            this.mList = list;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            MyHolder holder = new MyHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.tv.setText(mList.get(position));
            switch (position) {
                case 0:
                    holder.item.setBackgroundColor(Color.parseColor("#768905"));
                    break;
                case 1:
                    holder.item.setBackgroundColor(Color.parseColor("#589623"));
                    break;
                case 2:
                    holder.item.setBackgroundColor(Color.parseColor("#125965"));
                    break;
                case 3:
                    holder.item.setBackgroundColor(Color.parseColor("#698326"));
                    break;
                case 4:
                    holder.item.setBackgroundColor(Color.parseColor("#783692"));
                    break;
                case 5:
                    holder.item.setBackgroundColor(Color.parseColor("#768905"));
                    break;
                case 6:
                    holder.item.setBackgroundColor(Color.parseColor("#768905"));
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final RelativeLayout item;
        private final TextView tv;

        public MyHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
