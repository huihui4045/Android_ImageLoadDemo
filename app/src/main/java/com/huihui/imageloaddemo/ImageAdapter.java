package com.huihui.imageloaddemo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.huihui.imageloaddemo.load.ImageLoader;

/**
 * Created by gavin
 * Time 2017/7/12  17:49
 * Email:molu_clown@163.com
 */

public class ImageAdapter extends BaseAdapter {

    private String[] datas;

    private String TAG=this.getClass().getSimpleName();


    private LayoutInflater mInflater;
    private Drawable mDefaultBitmapDrawable;
    private int mImageWidth;

    private ImageLoader imageLoader;

    private  boolean mCanGetBitmapFromNetWork;
    private  boolean mIsGridViewIdle;

    public ImageAdapter(String[] datas, Context context, ImageLoader imageLoader, int mImageWidth,boolean mCanGetBitmapFromNetWork,boolean mIsGridViewIdle) {
        this.datas = datas;
        mInflater = LayoutInflater.from(context);
        mDefaultBitmapDrawable = context.getResources().getDrawable(R.mipmap.ic_launcher);
        this.imageLoader = imageLoader;
        this.mImageWidth = mImageWidth;
        this.mCanGetBitmapFromNetWork=mCanGetBitmapFromNetWork;
        this.mIsGridViewIdle=mIsGridViewIdle;
    }

    @Override
    public int getCount() {
        return datas.length;
    }

    @Override
    public Object getItem(int position) {
        return datas[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.item, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }


        ImageView imageView = viewHolder.imageView;

        String tag = (String) imageView.getTag();

        String data = datas[position];

        if (!data.equals(tag)) {
            imageView.setImageDrawable(mDefaultBitmapDrawable);
        }


        Log.e(TAG,"mIsGridViewIdle:"+mIsGridViewIdle);
        Log.e(TAG,"mCanGetBitmapFromNetWork:"+mCanGetBitmapFromNetWork);


        if (mIsGridViewIdle&&mCanGetBitmapFromNetWork){

            imageView.setTag(data);

            imageLoader.loadBitmap(data, imageView, mImageWidth, mImageWidth);
        }




        return convertView;
    }

    private static class ViewHolder {
        public ImageView imageView;
    }
}
