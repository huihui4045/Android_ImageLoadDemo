package com.huihui.imageloaddemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.GridView;

import com.huihui.imageloaddemo.load.ImageLoader;
import com.huihui.imageloaddemo.load.MyUtils;

public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener {

    private GridView mGridView;
    private ImageLoader mImageLoader;
    private int mImageWidth;
    private boolean mIsWifi;
    private boolean mCanGetBitmapFromNetWork;

    private ImageAdapter imageAdapter;

    private boolean mIsGridViewIdle=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = ((GridView) findViewById(R.id.gridView));


        mImageLoader = ImageLoader.build(MainActivity.this);

        int screenWidth = MyUtils.getScreenMetrics(this).widthPixels;
        int space = (int) MyUtils.dp2px(this, 20f);
        mImageWidth = (screenWidth - space) / 3;
        mIsWifi = MyUtils.isWifi(this);
        if (mIsWifi) {

            mCanGetBitmapFromNetWork = true;
        }


        imageAdapter = new ImageAdapter(Images.imageThumbUrls, this, mImageLoader, mImageWidth, mCanGetBitmapFromNetWork,mIsGridViewIdle);


        mGridView.setAdapter(imageAdapter);
        mGridView.setOnScrollListener(this);

        if (!mIsWifi) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("初次使用会从网络下载大概5MB的图片，确认要下载吗？");
            builder.setTitle("注意");
            builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mCanGetBitmapFromNetWork = true;
                    imageAdapter.notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("否", null);
            builder.show();

        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {

            mIsGridViewIdle = true;

            Log.e("onScrollStateChanged","=================");


            imageAdapter.notifyDataSetChanged();

        } else {

            mIsGridViewIdle = false;

        }


    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


    }

}
