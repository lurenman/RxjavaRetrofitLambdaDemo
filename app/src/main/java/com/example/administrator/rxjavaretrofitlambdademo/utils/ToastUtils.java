package com.example.administrator.rxjavaretrofitlambdademo.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class ToastUtils
{
    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        @Override
        public void run() {
            mToast.cancel();
        }
    };

    public static void showToast(Context mContext, String text, int duration) {

        mHandler.removeCallbacks(r);
        if (mToast != null) {
            mToast.setText(text);
        } else {
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        }
        mHandler.postDelayed(r, duration);

        mToast.show();
    }

    public static void showToast(Context mContext, int resId, int duration) {
        showToast(mContext, mContext.getResources().getString(resId), duration);
    }
}
