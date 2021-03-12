package com.future.message.util;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.future.message.R;

import java.util.Arrays;
import java.util.List;

/**
 * Author: JfangZ
 * Email: zhangjingfang@jeejio.com
 * Date: 2021/3/10 09:15
 * Description:
 */
public class CustomPermission {
    // 是否循环检查，true循环检查，false不是。默认为不是
    protected boolean mIsCircleCheck;
    protected FragmentActivity mFragmentActivity;
    // 完成检查，回调完成接口
    protected CustomPermission.OnFinishedCheck mOnFinishedCheck;
    // 记录弹出系统弹框之前是否是"不再提示"
    protected boolean mInitShouldShowRequestPermissionRationale = true;
    private String[] mPermissions;
    private String[] mDeniedPermissions;

    public CustomPermission(FragmentActivity fragmentActivity, String... permission) {
        this(fragmentActivity, false, permission);
    }

    public CustomPermission(FragmentActivity fragmentActivity, CustomPermission.OnFinishedCheck onFinishedCheck, String... permission) {
        this(fragmentActivity, false, onFinishedCheck, permission);
    }

    /**
     * @param fragmentActivity FragmentActivity的上下文
     * @param isCircleCheck    是否循环检查
     */
    public CustomPermission(FragmentActivity fragmentActivity, boolean isCircleCheck, String... permission) {
        this.mFragmentActivity = fragmentActivity;
        this.mIsCircleCheck = isCircleCheck;
        this.mPermissions = permission;
        handleCheck();
    }

    /**
     * @param fragmentActivity FragmentActivity的上下文
     * @param isCircleCheck    是否循环检查
     */
    public CustomPermission(FragmentActivity fragmentActivity, boolean isCircleCheck, CustomPermission.OnFinishedCheck onFinishedCheck, String... permission) {
        this.mFragmentActivity = fragmentActivity;
        this.mIsCircleCheck = isCircleCheck;
        this.mOnFinishedCheck = onFinishedCheck;
        this.mPermissions = permission;
        handleCheck();
    }

    /**
     * 处理检查权限或服务
     */
    public void handleCheck() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : mPermissions) {
                // 有"不再提示"的记录一下
                if (!mFragmentActivity.shouldShowRequestPermissionRationale(permission)) {
                    mInitShouldShowRequestPermissionRationale = false;
                }
            }
        }
        String[] deniedPermissions = new String[mPermissions.length];
        int index = 0;
        for (String permission : mPermissions) {
            if (!checkPermission(App.getInstance(), permission)) {
                deniedPermissions[index++] = permission;
            }
        }
        mDeniedPermissions = Arrays.copyOfRange(deniedPermissions, 0, index);
        if (mDeniedPermissions.length == 0) {
            // 获取到所有权限，完成检查
            finished();
        } else {
//            requestDialog();
        }
    }

    private boolean checkPermission(Context context, String permission) {
        return (PermissionChecker.checkSelfPermission(context, permission) == 0);
    }
//
//    /**
//     * 请求弹框
//     */
//    public void requestDialog() {
//        // 系统权限的请求框
//        PermissionUtil.requestPermission(mFragmentActivity.getSupportFragmentManager(), new IOnRequestPermissionResultCallBack() {
//            @Override
//            public void onSuccess() {
//                isCircle();
//            }
//
//            @Override
//            public void onFailure(List<String> list) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    boolean shouldShowRequestPermissionRationale = true;
//                    // 遍历集合，获取有"不再提示"的权限
//                    for (String deniedPermission : list) {
//                        if (!mFragmentActivity.shouldShowRequestPermissionRationale(deniedPermission)) {
//                            shouldShowRequestPermissionRationale = false;
//                        }
//                    }
//                    if (shouldShowRequestPermissionRationale) {
//                        isCircle();
//                    } else {
//                        if (mInitShouldShowRequestPermissionRationale) {
//                            isCircle();
//                        } else {
//                            // 有"来自系统权限弹框"的"不再提示"
//                            customDontShowAgainPermissionsDialog(list);
//                        }
//                    }
//                } else {
//                    isCircle();
//                }
//            }
//        }, mDeniedPermissions);
//    }
//
//    ;
//
//
//    /**
//     * 自定义不再提示弹框
//     */
//    public void customDontShowAgainPermissionsDialog(List<String> dontShowAgainPermissions) {
//        PermissionDialog.newInstance(createDontShowAgainPermissionsText(dontShowAgainPermissions))
//                .setOnSettingActivityResultListener(new PermissionDialog.OnSettingActivityResultListener() {
//                    @Override
//                    public void onSettingActivityResult() {
//                        isCircle();
//                    }
//                })
//                .show(mFragmentActivity.getSupportFragmentManager());
//    }
//
//    /**
//     * 创建不再提示自定义弹框的提示语
//     *
//     * @param dontShowAgainPermissions
//     * @return
//     */
//    private String createDontShowAgainPermissionsText(List<String> dontShowAgainPermissions) {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String permission : dontShowAgainPermissions) {
//            if (TextUtils.equals(permission, Manifest.permission.CAMERA)) {
//                stringBuilder.append(App.getInstance().getString(R.string.common_permission_denied_camera)).append("\n");
//            }
//            if (TextUtils.equals(permission, Manifest.permission.ACCESS_FINE_LOCATION)) {
//                stringBuilder.append(App.getInstance().getString(R.string.common_permission_denied_access_fine_location)).append("\n");
//            }
//            if (TextUtils.equals(permission, Manifest.permission.RECORD_AUDIO)) {
//                stringBuilder.append(App.getInstance().getString(R.string.common_permission_denied_record_audio)).append("\n");
//            }
//            if (TextUtils.equals(permission, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                stringBuilder.append(App.getInstance().getString(R.string.common_permission_denied_external_storage)).append("\n");
//            }
//        }
//        return stringBuilder.toString();
//    }

    /**
     * 是否循环检查权限
     */
    public final void isCircle() {
        if (!mIsCircleCheck) {
            // 仅检查一次
            finished();
        } else {
            // 循环查一次
            handleCheck();
        }
    }

    /**
     * 完成权限检查
     */
    public final void finished() {
        // 完成检查
        if (mOnFinishedCheck != null) {
            mOnFinishedCheck.finishedCheck();
        }
    }


    public interface OnFinishedCheck {
        void finishedCheck();
    }

    public interface IOnRequestPermissionResultCallBack {


    }


}
