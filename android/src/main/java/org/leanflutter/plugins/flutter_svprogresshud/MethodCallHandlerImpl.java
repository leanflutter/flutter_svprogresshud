package org.leanflutter.plugins.flutter_svprogresshud;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.widget.ImageView;

import com.kaopiz.kprogresshud.KProgressHUD;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;


class MethodCallHandlerImpl implements MethodChannel.MethodCallHandler {
    private Activity activity;
    private KProgressHUD hud;

    private float cornerRadius = 14;

    public MethodCallHandlerImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        switch (call.method) {
            case "show":
                this.show(call, result);
                break;
            case "showProgress":
                this.showProgress(call, result);
                break;
            case "dismiss":
                this.dismiss(call, result);
                break;
            case "showInfo":
                this.showInfo(call, result);
                break;
            case "showSuccess":
                this.showSuccess(call, result);
                break;
            case "showError":
                this.showError(call, result);
                break;
            case "setDefaultStyle":
                this.setDefaultStyle(call, result);
                break;
            case "setDefaultMaskType":
                this.setDefaultMaskType(call, result);
                break;
            case "setDefaultAnimationType":
                this.setDefaultAnimationType(call, result);
                break;
            case "setMinimumSize":
                this.setMinimumSize(call, result);
                break;
            case "setRingThickness":
                this.setRingThickness(call, result);
                break;
            case "setRingRadius":
                this.setRingRadius(call, result);
                break;
            case "setRingNoTextRadius":
                this.setRingNoTextRadius(call, result);
                break;
            case "setCornerRadius":
                this.setCornerRadius(call, result);
                break;
            case "setHapticsEnabled":
                this.setHapticsEnabled(call, result);
                break;
            default:
                result.notImplemented();
                break;
        }
    }

    private void show(MethodCall call, MethodChannel.Result result) {
        String status = call.argument("status");

        hud = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCornerRadius(cornerRadius)
                .setLabel(status)
                .show();
    }

    private void showProgress(MethodCall call, MethodChannel.Result result) {
        Number progress = 0;
        String status = call.argument("status");

        if (call.hasArgument("progress")) {
            progress = (Number) call.argument("progress");
        }

        if (hud == null) {
            hud = KProgressHUD.create(activity)
                    .setStyle(KProgressHUD.Style.ANNULAR_DETERMINATE)
                    .setCornerRadius(cornerRadius)
                    .setMaxProgress(100);
        }

        hud.setLabel(status);
        hud.setProgress((int) (progress.doubleValue() * 100));

        if (!hud.isShowing()) {
            hud.show();
        }
    }

    private void dismiss(MethodCall call, final MethodChannel.Result result) {
        int delay = 0;
        if (call.hasArgument("delay")) delay = (int) call.argument("delay");

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (hud != null && hud.isShowing()) {
                hud.dismiss();
                hud = null;

                result.success(true);
            }
        }, delay);
    }

    private void showInfo(MethodCall call, MethodChannel.Result result) {
        String status = call.argument("status");

        ImageView imageView = new ImageView(activity);
        imageView.setImageResource(R.mipmap.info);
        imageView.setColorFilter(Color.WHITE);

        hud = KProgressHUD.create(activity)
                .setCornerRadius(cornerRadius)
                .setCustomView(imageView)
                .setLabel(status)
                .show();
    }

    private void showSuccess(MethodCall call, MethodChannel.Result result) {
        String status = call.argument("status");

        ImageView imageView = new ImageView(activity);
        imageView.setImageResource(R.mipmap.success);
        imageView.setColorFilter(Color.WHITE);

        hud = KProgressHUD.create(activity)
                .setCornerRadius(cornerRadius)
                .setCustomView(imageView)
                .setLabel(status)
                .show();
    }

    private void showError(MethodCall call, MethodChannel.Result result) {
        String status = call.argument("status");

        ImageView imageView = new ImageView(activity);
        imageView.setImageResource(R.mipmap.error);
        imageView.setColorFilter(Color.WHITE);

        hud = KProgressHUD.create(activity)
                .setCornerRadius(cornerRadius)
                .setCustomView(imageView)
                .setLabel(status)
                .show();
    }

    private void setDefaultStyle(MethodCall call, MethodChannel.Result result) {

    }

    private void setDefaultMaskType(MethodCall call, MethodChannel.Result result) {

    }

    private void setDefaultAnimationType(MethodCall call, MethodChannel.Result result) {

    }

    private void setMinimumSize(MethodCall call, MethodChannel.Result result) {

    }

    private void setRingThickness(MethodCall call, MethodChannel.Result result) {

    }

    private void setRingRadius(MethodCall call, MethodChannel.Result result) {

    }

    private void setRingNoTextRadius(MethodCall call, MethodChannel.Result result) {

    }

    private void setCornerRadius(MethodCall call, MethodChannel.Result result) {
        Number cornerRadius = (Number) call.argument("cornerRadius");
        this.cornerRadius = cornerRadius.floatValue();
    }

    private void setHapticsEnabled(MethodCall call, MethodChannel.Result result) {

    }
}

