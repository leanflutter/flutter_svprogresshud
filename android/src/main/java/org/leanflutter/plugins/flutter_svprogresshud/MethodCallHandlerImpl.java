package org.leanflutter.plugins.flutter_svprogresshud;

import android.app.Activity;
import android.os.Handler;

import org.leanflutter.svprogresshud.SVProgressHUD;
import org.leanflutter.svprogresshud.SVProgressHUDAnimationType;
import org.leanflutter.svprogresshud.SVProgressHUDMaskType;
import org.leanflutter.svprogresshud.SVProgressHUDStyle;
import org.leanflutter.svprogresshud.SVSize;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

class MethodCallHandlerImpl implements MethodChannel.MethodCallHandler {
    private Activity activity;
    private SVProgressHUD svProgressHUD;

    public MethodCallHandlerImpl(Activity activity) {
        this.activity = activity;
        this.svProgressHUD = new SVProgressHUD(this.activity);
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
        svProgressHUD.showWithStatus(status);
    }

    private void showProgress(MethodCall call, MethodChannel.Result result) {
        Number progress = 0;
        String status = call.argument("status");

        if (call.hasArgument("progress")) {
            progress = (Number) call.argument("progress");
        }

        svProgressHUD.showProgress(progress.floatValue(), status);

        if (!svProgressHUD.isVisible()) {
            svProgressHUD.show();
        }
    }

    private void dismiss(MethodCall call, final MethodChannel.Result result) {
        int delay = 0;
        if (call.hasArgument("delay")) delay = (int) call.argument("delay");

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            svProgressHUD.dismiss();
            result.success(true);
        }, delay);
    }

    private void showInfo(MethodCall call, MethodChannel.Result result) {
        String status = call.argument("status");
        svProgressHUD.showInfoWithStatus(status);
    }

    private void showSuccess(MethodCall call, MethodChannel.Result result) {
        String status = call.argument("status");
        svProgressHUD.showSuccessWithStatus(status);
    }

    private void showError(MethodCall call, MethodChannel.Result result) {
        String status = call.argument("status");
        svProgressHUD.showErrorWithStatus(status);
    }

    private void setDefaultStyle(MethodCall call, MethodChannel.Result result) {
        String style = (String) call.argument("style");
        svProgressHUD.setDefaultStyle(SVProgressHUDStyle.fromString(style));
    }

    private void setDefaultMaskType(MethodCall call, MethodChannel.Result result) {
        String maskType = (String) call.argument("maskType");
        svProgressHUD.setDefaultMaskType(SVProgressHUDMaskType.fromString(maskType));
    }

    private void setDefaultAnimationType(MethodCall call, MethodChannel.Result result) {
        String type = (String) call.argument("type");
        svProgressHUD.setDefaultAnimationType(SVProgressHUDAnimationType.fromString(type));
    }

    private void setMinimumSize(MethodCall call, MethodChannel.Result result) {
        Number width = (Number) call.argument("width");
        Number height = (Number) call.argument("height");
        svProgressHUD.setMinimumSize(new SVSize(width.floatValue(), height.floatValue()));
    }

    private void setRingThickness(MethodCall call, MethodChannel.Result result) {
        Number ringThickness = (Number) call.argument("ringThickness");
        svProgressHUD.setRingThickness(ringThickness.floatValue());
    }

    private void setRingRadius(MethodCall call, MethodChannel.Result result) {
        Number radius = (Number) call.argument("radius");
        svProgressHUD.setRingRadius(radius.floatValue());
    }

    private void setRingNoTextRadius(MethodCall call, MethodChannel.Result result) {
        Number radius = (Number) call.argument("radius");
        svProgressHUD.setRingNoTextRadius(radius.floatValue());
    }

    private void setCornerRadius(MethodCall call, MethodChannel.Result result) {
        Number cornerRadius = (Number) call.argument("cornerRadius");
        svProgressHUD.setCornerRadius(cornerRadius.floatValue());
    }

    private void setForegroundColor(MethodCall call, MethodChannel.Result result) {

    }

    private void setForegroundImageColor(MethodCall call, MethodChannel.Result result) {

    }

    private void setBackgroundColor(MethodCall call, MethodChannel.Result result) {

    }

    private void setBackgroundLayerColor(MethodCall call, MethodChannel.Result result) {
    }


    private void setHapticsEnabled(MethodCall call, MethodChannel.Result result) {
        boolean hapticsEnabled = (boolean) call.argument("hapticsEnabled");
        svProgressHUD.setHapticsEnabled(hapticsEnabled);
    }
}

