package org.blankapp.flutterplugins.flutter_svprogresshud;

import android.app.Activity;
import android.os.Handler;
import android.widget.ImageView;

import com.kaopiz.kprogresshud.KProgressHUD;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * FlutterSvprogresshudPlugin
 */
public class FlutterSvprogresshudPlugin implements MethodCallHandler {
    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_svprogresshud");
        channel.setMethodCallHandler(new FlutterSvprogresshudPlugin(registrar.activity()));
    }


    private final Activity currentActivity;
    private KProgressHUD hud;

    public FlutterSvprogresshudPlugin(Activity activity) {
        super();
        this.currentActivity = activity;
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        double progress = 0;
        String status = null;
        int delay = 0;

        if (call.hasArgument("progress")) progress = call.argument("progress");
        if (call.hasArgument("status")) status = call.argument("status");
        if (call.hasArgument("delay")) delay = call.argument("delay");

        if (call.method.equals("show")) {
            this.show(status);
        } else if (call.method.equals("showInfo")) {
            this.showInfo(status);
        } else if (call.method.equals("showSuccess")) {
            this.showSuccess(status);
        } else if (call.method.equals("showError")) {
            this.showError(status);
        } else if (call.method.equals("showProgress")) {
            this.showProgress(progress, status);
        } else if (call.method.equals("dismiss")) {
            this.dismiss(0);
        } else if (call.method.equals("dismissWithDelay")) {
            this.dismiss(delay);
        } else {
            result.notImplemented();
            return;
        }

        result.success(true);
    }

    public void show(String status) {
        dismiss(0);
        hud = KProgressHUD.create(currentActivity);
        if (status != null && !status.isEmpty()) {
            hud.setLabel(status);
        }
        hud.show();
    }

    public void showInfo(String status) {
        dismiss(0);
        ImageView imageView = new ImageView(currentActivity);
        imageView.setBackgroundResource(R.mipmap.info);

        hud = KProgressHUD.create(currentActivity)
                .setCustomView(imageView)
                .setLabel(status);
        hud.show();
    }

    public void showSuccess(String status) {
        dismiss(0);
        ImageView imageView = new ImageView(currentActivity);
        imageView.setBackgroundResource(R.mipmap.success);

        hud = KProgressHUD.create(currentActivity)
                .setCustomView(imageView)
                .setLabel(status);
        hud.show();
    }

    public void showError(String status) {
        dismiss(0);
        ImageView imageView = new ImageView(currentActivity);
        imageView.setBackgroundResource(R.mipmap.error);

        hud = KProgressHUD.create(currentActivity)
                .setCustomView(imageView)
                .setLabel(status);
        hud.show();
    }

    public void showProgress(Double progress, String status) {
        if (hud == null) {
            hud = KProgressHUD.create(currentActivity)
                    .setStyle(KProgressHUD.Style.BAR_DETERMINATE)
                    .setMaxProgress(100)
                    .setLabel(status);
        }
        hud.setProgress(progress.intValue());
        if (!hud.isShowing()) {
            hud.show();
        }
    }

    public void dismiss(Integer delay) {
        int delayMillis = delay != null ? delay.intValue() : 0;
        if (delayMillis == 0) {
            if (hud != null) {
                hud.dismiss();
                hud = null;
            }
        } else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (hud != null) {
                        hud.dismiss();
                        hud = null;
                    }
                }
            }, delayMillis);
        }
    }
}
