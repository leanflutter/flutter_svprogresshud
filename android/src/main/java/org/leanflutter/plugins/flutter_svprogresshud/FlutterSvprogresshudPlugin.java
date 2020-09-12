package org.leanflutter.plugins.flutter_svprogresshud;

import android.app.Activity;
import android.os.Handler;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.kaopiz.kprogresshud.KProgressHUD;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * FlutterSvprogresshudPlugin
 */
public class FlutterSvprogresshudPlugin implements FlutterPlugin, ActivityAware, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private MethodChannel channel;

    private Activity activity;
    private KProgressHUD hud;

    public FlutterSvprogresshudPlugin() {
    }

    private FlutterSvprogresshudPlugin(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "flutter_svprogresshud");
        channel.setMethodCallHandler(this);
    }

    // This static function is optional and equivalent to onAttachedToEngine. It supports the old
    // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
    // plugin registration via this function while apps migrate to use the new Android APIs
    // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
    //
    // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
    // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
    // depending on the user's project. onAttachedToEngine or registerWith must both be defined
    // in the same class.
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_svprogresshud");
        channel.setMethodCallHandler(new FlutterSvprogresshudPlugin(registrar.activity()));
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        this.activity = binding.getActivity();
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity();
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        onAttachedToActivity(binding);
    }

    @Override
    public void onDetachedFromActivity() {
        this.activity = null;
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        double progress = 0;
        String status = null;
        int delay = 0;

        if (call.hasArgument("progress")) progress = call.argument("progress");
        if (call.hasArgument("status")) status = call.argument("status");
        if (call.hasArgument("delay")) delay = call.argument("delay");

        if (call.method.equals("setDefaultStyle")) {
            // No-op
        } else if (call.method.equals("setDefaultMaskType")) {
            // No-op
        } else if (call.method.equals("show")) {
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

    private void show(String status) {
        dismiss(0);
        hud = KProgressHUD.create(activity);
        if (status != null && !status.isEmpty()) {
            hud.setLabel(status);
        }
        hud.show();
    }

    private void showInfo(String status) {
        dismiss(0);
        ImageView imageView = new ImageView(activity);
        imageView.setBackgroundResource(R.mipmap.info);

        hud = KProgressHUD.create(activity)
                .setCustomView(imageView)
                .setLabel(status);
        hud.show();
    }

    private void showSuccess(String status) {
        dismiss(0);
        ImageView imageView = new ImageView(activity);
        imageView.setBackgroundResource(R.mipmap.success);

        hud = KProgressHUD.create(activity)
                .setCustomView(imageView)
                .setLabel(status);
        hud.show();
    }

    private void showError(String status) {
        dismiss(0);
        ImageView imageView = new ImageView(activity);
        imageView.setBackgroundResource(R.mipmap.error);

        hud = KProgressHUD.create(activity)
                .setCustomView(imageView)
                .setLabel(status);
        hud.show();
    }

    private void showProgress(Double progress, String status) {
        if (hud == null) {
            hud = KProgressHUD.create(activity)
                    .setStyle(KProgressHUD.Style.BAR_DETERMINATE)
                    .setMaxProgress(100);
        }
        hud.setProgress(progress.intValue());
        if (status != null && !status.isEmpty()) {
            hud.setLabel(status);
        }
        if (!hud.isShowing()) {
            hud.show();
        }
    }

    private void dismiss(Integer delay) {
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
