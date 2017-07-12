package com.huihui.imageloaddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.huihui.imageloaddemo.load.ImageLoader;
import com.huihui.imageloaddemo.load.MyUtils;

public class MainActivity extends AppCompatActivity {

    private GridView mGridView;
    private ImageLoader mImageLoader;
    private int mImageWidth;
    private boolean mIsWifi;
    private boolean mCanGetBitmapFromNetWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = ((GridView) findViewById(R.id.gridView));


        mImageLoader = ImageLoader.build(MainActivity.this);

        int screenWidth = MyUtils.getScreenMetrics(this).widthPixels;
        int space = (int)MyUtils.dp2px(this, 20f);
        mImageWidth = (screenWidth - space) / 3;
        mIsWifi = MyUtils.isWifi(this);
        if (mIsWifi) {
            mCanGetBitmapFromNetWork = true;
        }


        ImageAdapter imageAdapter=new ImageAdapter(Images.imageThumbUrls,this,mImageLoader,mImageWidth);


        mGridView.setAdapter(imageAdapter);

    }
}
