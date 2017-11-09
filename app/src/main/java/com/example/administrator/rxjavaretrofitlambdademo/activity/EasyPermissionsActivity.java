package com.example.administrator.rxjavaretrofitlambdademo.activity;

import android.widget.TextView;
import com.example.administrator.rxjavaretrofitlambdademo.R;
import com.example.administrator.rxjavaretrofitlambdademo.constants.ConstantsUtils;
import com.example.administrator.rxjavaretrofitlambdademo.utils.ToastUtils;
import com.jakewharton.rxbinding.view.RxView;
import java.util.List;
import java.util.concurrent.TimeUnit;
import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class EasyPermissionsActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    @BindView(R.id.tv_requestpermission)
    TextView tv_Requestpermission;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_easypermissions);
        ButterKnife.bind(this);
    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
        RxView.clicks(tv_Requestpermission).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                    //先判断有没有这个权限
                if (EasyPermissions.hasPermissions(mContext, android.Manifest.permission.CAMERA)) {
                    ToastUtils.showToast(getApplicationContext(), "已经获取相机权限0", 1000);

                } else {
                    //此时我们需要请求该权限
                    EasyPermissions.requestPermissions(mContext, "需要相机权限", ConstantsUtils.TAKEPHOTO_PERMISSION, android.Manifest.permission.CAMERA);
                }
            }
        });
    }

    @Override
    protected void loadData() {

    }


    //把请求权限回调交个EasyPermissions
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == ConstantsUtils.TAKEPHOTO_PERMISSION) {
            if (perms.contains(android.Manifest.permission.CAMERA)) {
                ToastUtils.showToast(getApplicationContext(), "已经获取相机权限1", 1000);
            }
        }
    }

    //权限被禁止，只要没有权限对话框消失就调用此方法
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == ConstantsUtils.TAKEPHOTO_PERMISSION) {
            if (perms.contains(android.Manifest.permission.CAMERA)) {
                ToastUtils.showToast(getApplicationContext(), "没有相机权限，请手动授予", 1000);
            }
        }

    }


}
