package org.leanflutter.plugins.flutter_svprogresshud;

import android.app.Activity;

import org.leanflutter.svprogresshud.SVProgressHUD;
import org.leanflutter.svprogresshud.SVProgressHUDAnimationType;
import org.leanflutter.svprogresshud.SVProgressHUDDismissCompletion;
import org.leanflutter.svprogresshud.SVProgressHUDMaskType;
import org.leanflutter.svprogresshud.SVProgressHUDStyle;

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
            case "setBorderColor":
                this.setBorderColor(call, result);
                break;
            case "setBorderWidth":
                this.setBorderWidth(call, result);
                break;
            case "setForegroundColor":
                this.setForegroundColor(call, result);
                break;
            case "setForegroundImageColor":
                this.setForegroundImageColor(call, result);
                break;
            case "setBackgroundColor":
                this.setBackgroundColor(call, result);
                break;
            case "setBackgroundLayerColor":
                this.setBackgroundLayerColor(call, result);
                break;
            case "setImageViewSize":
                this.setImageViewSize(call, result);
                break;
            case "setShouldTintImages":
                this.setShouldTintImages(call, result);
                break;
            case "setMinimumDismissTimeInterval":
                this.setMinimumDismissTimeInterval(call, result);
                break;
            case "setMaximumDismissTimeInterval":
                this.setMaximumDismissTimeInterval(call, result);
                break;
            case "setFadeInAnimationDuration":
                this.setFadeInAnimationDuration(call, result);
                break;
            case "setFadeOutAnimationDuration":
                this.setFadeOutAnimationDuration(call, result);
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

        svProgressHUD.dismissWithDelay(delay > 0 ? (delay / 1000f) : 0, () -> result.success(true));
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
        svProgressHUD.setMinimumSize(width.floatValue(), height.floatValue());
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

    private void setBorderColor(MethodCall call, MethodChannel.Result result) {
        Number color = (Number) call.argument("color");
        svProgressHUD.setBorderColor(color.intValue());
    }

    private void setBorderWidth(MethodCall call, MethodChannel.Result result) {
        Number width = (Number) call.argument("width");
        svProgressHUD.setBorderWidth(width.floatValue());
    }

    private void setForegroundColor(MethodCall call, MethodChannel.Result result) {
        Number color = (Number) call.argument("color");
        svProgressHUD.setForegroundColor(color.intValue());
    }

    private void setForegroundImageColor(MethodCall call, MethodChannel.Result result) {
        Number color = (Number) call.argument("color");
        svProgressHUD.setForegroundImageColor(color.intValue());
    }

    private void setBackgroundColor(MethodCall call, MethodChannel.Result result) {
        Number color = (Number) call.argument("color");
        svProgressHUD.setBackgroundColor(color.intValue());
    }

    private void setBackgroundLayerColor(MethodCall call, MethodChannel.Result result) {
        Number color = (Number) call.argument("color");
        svProgressHUD.setBackgroundLayerColor(color.intValue());
    }

    public void setImageViewSize(MethodCall call, MethodChannel.Result result) {
        Number width = (Number) call.argument("width");
        Number height = (Number) call.argument("height");
        svProgressHUD.setImageViewSize(width.floatValue(), height.floatValue());
    }

    public void setShouldTintImages(MethodCall call, MethodChannel.Result result) {
        boolean shouldTintImages = (boolean) call.argument("shouldTintImages");
        svProgressHUD.setShouldTintImages(shouldTintImages);
    }

    public void setMinimumDismissTimeInterval(MethodCall call, MethodChannel.Result result) {
        Number interval = call.argument("interval");
        svProgressHUD.setMinimumDismissTimeInterval(interval.floatValue());
    }

    public void setMaximumDismissTimeInterval(MethodCall call, MethodChannel.Result result) {
        Number interval = call.argument("interval");
        svProgressHUD.setMaximumDismissTimeInterval(interval.floatValue());
    }

    public void setFadeInAnimationDuration(MethodCall call, MethodChannel.Result result) {
        Number duration = (Number) call.argument("duration");
        svProgressHUD.setFadeInAnimationDuration(duration.floatValue());
    }

    public void setFadeOutAnimationDuration(MethodCall call, MethodChannel.Result result) {
        Number duration = (Number) call.argument("duration");
        svProgressHUD.setFadeOutAnimationDuration(duration.floatValue());
    }

    private void setHapticsEnabled(MethodCall call, MethodChannel.Result result) {
        boolean hapticsEnabled = (boolean) call.argument("hapticsEnabled");
        svProgressHUD.setHapticsEnabled(hapticsEnabled);
    }
}

