package com.example.administrator.rxjavaretrofitlambdademo.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class CommonDialog extends ProgressDialog
{

    public CommonDialog(Context context, String str) {
        super(context);
        //我发现这里不能用application的那个context，因为要show这个dialog
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
//        setProgressStyle(STYLE_SPINNER);
//        setContentView();
        setMessage(str);
    }
}
