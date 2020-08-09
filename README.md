# MaskPageDemo
最好用的遮罩引导页

## 实现思路
开发过程中我们经常会遇到这样的需求：第一次进入APP需要给用户引导，某些功能的按钮要高亮显示，我们最简单的做法是<br>
让UI给我们切图，高亮的VIEW和底图整体是一张图片，我们加载这张图片就好了，但是这样会有这样的一个问题：由于安卓手机机型太多<br>
图片会显示不全，体验不好。所以我们要换一种思路，首先我们要拿到高亮的VIEW的位置信息，然后在相对布局里，把指示的图片利用VIEW的<br>
setX和setY方法，移动到高亮的位置，所以OnMaskLoader接口需要开发者自己实现 遮罩利用dialog实现

## 使用方法

                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_dialog, null);//dialog里的布局文件
                final MaskPageView mpv = view.findViewById(R.id.mpv); 
                final CustomDialog dialog = new CustomDialog(MainActivity.this, view, Gravity.CENTER).setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override 
                    public void onDismiss(DialogInterface dialog) { 
                        mpv.onDestroy(); 
                    } 
                }).setCanceledOnTouchOutside(false).setMax(true, true).build();
                mpv.setMackListener(new OnMackListener() {//点击事件 
                    @Override <br>
                    public void OnClick(View v, int position) { 
                        switch (v.getId()) { 
                            case R.id.target_view:
                                dialog.dismiss(); 
                                break; 
                        } 
                    } 

                    @Override <br>
                    public void onFinish() { //引导结束
                        dialog.dismiss(); 
                    } 
                }).setMaskLoader(new BaseMaskLoader()).build(resIds, highViews); 
                dialog.show(); 
                
                resIds：引导页的布局文件
                highViews：高亮的VIEW
                注意：这两个的数量要一致，否则不执行
                BaseMaskLoader：具体的逻辑处理，控制布局用的
                效果图：直接下载本项目，运行就能看到项目效果
                MaskInfo：高亮的VIEW的位置信息保存在这个类里
                
                layout_dialog布局文件：
                <?xml version="1.0" encoding="utf-8"?>
               <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                android:layout_width="match_parent"
                android:layout_height="match_parent">

               <com.sky.maskpage.view.MaskPageView
               android:id="@+id/mpv"
               android:layout_width="match_parent"
               android:layout_height="match_parent" />
               </RelativeLayout>
               
## 写在最后的话              
    
 如果觉得好用，就麻烦在github上点个赞，星星越多，作者越有动力
 QQ技术交流群：128805502
               
               
               
               
               
               
               
               
               
               
               
               
               
               
               
               
               
               
               
               
               
               
               
               
               
               
               
               
