package com.huihui.imageloaddemo.load;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileDescriptor;

/**
 * Created by gavin
 * Time 2017/7/12  14:29
 * Email:molu_clown@163.com
 */

public class ImageResizer {

    public ImageResizer() {
    }


    public static Bitmap decodeSampledBitmapFromFileDescriptor(FileDescriptor fileDescriptor, int reqWidth, int reqHeight) {

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);

        int inSimpleSize = calculateInSimpleSize(options, reqWidth, reqHeight);

        options.inSampleSize = inSimpleSize;

        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);

    }


    public static Bitmap decodeSampledBitmapFromResource(Resources resources, int resId, int reqWidth, int reqHeight) {

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(resources, resId, options);

        int inSimpleSize = calculateInSimpleSize(options, reqWidth, reqHeight);

        options.inSampleSize = inSimpleSize;

        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(resources, resId, options);
    }


    /*****
     * 计算压缩比
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private static int calculateInSimpleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {


        if (reqHeight == 0 || reqWidth == 0) {

            return 1;
        }

        final int height = options.outHeight;
        final int width = options.outWidth;

        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {


            int halfHeight = height / 2;

            int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {


                inSampleSize *= 2;
            }


        }

        return inSampleSize;

    }
}
