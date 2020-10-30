package org.leanflutter.svprogresshud;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.leanflutter.plugins.flutter_svprogresshud.R;

public class SVProgressHUD {
    private Activity activity;

    private ViewGroup decorView;

    private GradientDrawable hudViewBackgroundDrawable;
    private SVIndefiniteAnimatedView indefiniteAnimatedView;
    private UIActivityIndicatorView activityIndicatorView;
    private SVProgressAnimatedView progressAnimatedView;

    private FrameLayout contentView;
    private ViewGroup backgroundLayer;
    private SVRadialGradientLayer backgroundGradientLayer;
    private ViewGroup hudView;
    private FrameLayout imageContainer;
    private TextView statusLabel;

    private Animation animBackgroundLayerFadeIn;
    private Animation animBackgroundLayerFadeOut;

    private Animation animHudViewFadeIn;
    private Animation animHudViewFadeOut;

    private Handler dismissHandler = new Handler();
    private Runnable dismissAction;
    private Runnable dismissDelayAction;

    private boolean _isInitializing;

    // Customization
    private SVProgressHUDStyle defaultStyle;
    private SVProgressHUDMaskType defaultMaskType;
    private SVProgressHUDAnimationType defaultAnimationType;

    //@property (strong, nonatomic, nullable) UIView *containerView;                                // if nil then use default window level
    private float minimumWidth;
    private float minimumHeight;
    private float ringThickness;                                                                    // default is 2 pt
    private float ringRadius;                                                                       // default is 18 pt
    private float ringNoTextRadius;                                                                 // default is 24 pt
    private float cornerRadius;                                                                     // default is 14 pt
    private int borderColor;
    private float borderWidth;
    //@property (strong, nonatomic, nonnull) UIFont *font UI_APPEARANCE_SELECTOR;                   // default is [UIFont preferredFontForTextStyle:UIFontTextStyleSubheadline]
    private int backgroundColor;
    private int foregroundColor;
    private int foregroundImageColor;
    private int backgroundLayerColor;
    private float imageViewWidth;                                                                   // default is 28 pt
    private float imageViewHeight;                                                                  // default is 28 pt
    private boolean shouldTintImages;                                                               // default is YES
    private ImageView infoImage;
    private ImageView successImage;
    private ImageView errorImage;
    //@property (strong, nonatomic, nonnull) UIView *viewForExtension UI_APPEARANCE_SELECTOR;       // default is nil, only used if #define SV_APP_EXTENSIONS is set
    //@property (assign, nonatomic) NSTimeInterval graceTimeInterval;                               // default is 0 seconds
    private float minimumDismissTimeInterval;                                                       // default is 5.0 seconds
    private float maximumDismissTimeInterval;                                                       // default is CGFLOAT_MAX

    //@property (assign, nonatomic) UIOffset offsetFromCenter UI_APPEARANCE_SELECTOR; // default is 0, 0

    private float fadeInAnimationDuration;                                                          // default is 0.15
    private float fadeOutAnimationDuration;                                                         // default is 0.15

    //@property (assign, nonatomic) UIWindowLevel maxSupportedWindowLevel;                          // default is UIWindowLevelNormal

    boolean hapticsEnabled;                                                                         // default is NO
    boolean motionEffectEnabled;                                                                    // default is YES

    public SVProgressHUD(Activity activity) {
        this.activity = activity;
        this.init();
    }

    private void init() {
        _isInitializing = true;

        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        decorView = (activity).getWindow().getDecorView().findViewById(android.R.id.content);

        hudViewBackgroundDrawable = new GradientDrawable();
        hudViewBackgroundDrawable.setShape(GradientDrawable.RECTANGLE);
        indefiniteAnimatedView = new SVIndefiniteAnimatedView(activity);
        activityIndicatorView = new UIActivityIndicatorView(activity);
        progressAnimatedView = new SVProgressAnimatedView(activity);

        contentView = (FrameLayout) layoutInflater.inflate(R.layout.svprogresshud_layout, null, false);
        backgroundLayer = contentView.findViewById(R.id.backgroundLayer);
        backgroundGradientLayer = contentView.findViewById(R.id.backgroundGradientLayer);
        hudView = contentView.findViewById(R.id.hud_view);
        hudView.setBackground(hudViewBackgroundDrawable);
        imageContainer = contentView.findViewById(R.id.imageContainer);
        statusLabel = contentView.findViewById(R.id.statusLabel);

        animBackgroundLayerFadeIn = AnimationUtils.loadAnimation(activity, R.anim.svprogresshud_bg_fade_in);
        animBackgroundLayerFadeOut = AnimationUtils.loadAnimation(activity, R.anim.svprogresshud_bg_fade_out);

        animHudViewFadeIn = AnimationUtils.loadAnimation(activity, R.anim.svprogresshud_hudview_fade_in);
        animHudViewFadeOut = AnimationUtils.loadAnimation(activity, R.anim.svprogresshud_hudview_fade_out);

        infoImage = new ImageView(activity);
        infoImage.setImageResource(R.drawable.svprogresshud_info);

        successImage = new ImageView(activity);
        successImage.setImageResource(R.drawable.svprogresshud_success);

        errorImage = new ImageView(activity);
        errorImage.setImageResource(R.drawable.svprogresshud_error);

        setBackgroundColor(Color.WHITE);
        setForegroundColor(Color.BLACK);
        setBackgroundLayerColor(Color.argb(0x66, 0xff, 0xff, 0xff));

        // Set default values
        setDefaultMaskType(SVProgressHUDMaskType.None);
        setDefaultStyle(SVProgressHUDStyle.Light);
        setDefaultAnimationType(SVProgressHUDAnimationType.Flat);
        setMinimumSize(0, 0);
//        _font = [UIFont preferredFontForTextStyle:UIFontTextStyleSubheadline];

        setImageViewSize(28, 28);
        setShouldTintImages(true);

        setRingThickness(2.0f);
        setRingRadius(18.0f);
        setRingNoTextRadius(24.0f);

        setCornerRadius(14.0f);

//        _graceTimeInterval = 0.0f;
        setMinimumDismissTimeInterval(5.0f);
        setMaximumDismissTimeInterval(Float.MAX_VALUE);
//
        setFadeInAnimationDuration(0.15f);
        setFadeOutAnimationDuration(0.15f);

//        _maxSupportedWindowLevel = UIWindowLevelNormal;

        hapticsEnabled = false;
        motionEffectEnabled = true;

        _isInitializing = false;
    }

    public void setDefaultStyle(SVProgressHUDStyle style) {
        defaultStyle = style;

        switch (defaultStyle) {
            case Light:
                hudViewBackgroundDrawable.setColor(Color.WHITE);
                indefiniteAnimatedView.setStrokeColor(Color.BLACK);
                activityIndicatorView.setColorFilter(Color.BLACK);
                progressAnimatedView.setThumbColor(Color.argb(0x1a, 0x00, 0x00, 0x00));
                progressAnimatedView.setActiveColor(Color.BLACK);
                statusLabel.setTextColor(Color.BLACK);
                infoImage.setColorFilter(Color.BLACK);
                successImage.setColorFilter(Color.BLACK);
                errorImage.setColorFilter(Color.BLACK);
                break;
            case Dark:
                hudViewBackgroundDrawable.setColor(Color.BLACK);
                indefiniteAnimatedView.setStrokeColor(Color.WHITE);
                activityIndicatorView.setColorFilter(Color.WHITE);
                progressAnimatedView.setThumbColor(Color.argb(0x1a, 0xff, 0xff, 0xff));
                progressAnimatedView.setActiveColor(Color.WHITE);
                statusLabel.setTextColor(Color.WHITE);
                infoImage.setColorFilter(Color.WHITE);
                successImage.setColorFilter(Color.WHITE);
                errorImage.setColorFilter(Color.WHITE);
                break;
            case Custom:
                hudViewBackgroundDrawable.setColor(backgroundColor);
                indefiniteAnimatedView.setStrokeColor(foregroundColor);
                activityIndicatorView.setColorFilter(foregroundColor);
                progressAnimatedView.setThumbColor(Color.argb(0x1a, Color.red(foregroundColor), Color.green(foregroundColor), Color.blue(foregroundColor)));
                progressAnimatedView.setActiveColor(foregroundColor);
                statusLabel.setTextColor(foregroundColor);
                infoImage.setColorFilter(foregroundColor);
                successImage.setColorFilter(foregroundColor);
                errorImage.setColorFilter(foregroundColor);
                break;
        }
    }

    public void setDefaultMaskType(SVProgressHUDMaskType maskType) {
        defaultMaskType = maskType;

        backgroundLayer.setClickable(defaultMaskType != SVProgressHUDMaskType.None);
        backgroundGradientLayer.setVisibility(View.GONE);

        switch (defaultMaskType) {
            case None:
            case Clear:
                backgroundLayer.setBackgroundColor(Color.TRANSPARENT);
                break;
            case Black:
                backgroundLayer.setBackgroundColor(Color.argb(0x66, 0x00, 0x00, 0x00));
                break;
            case Gradient:
                backgroundLayer.setBackgroundColor(Color.TRANSPARENT);
                backgroundGradientLayer.setVisibility(View.VISIBLE);
                break;
            case Custom:
                backgroundLayer.setBackgroundColor(backgroundLayerColor);
                break;
        }
    }

    public void setDefaultAnimationType(SVProgressHUDAnimationType type) {
        this.defaultAnimationType = type;

        if (!(isVisible() && imageContainer.getChildCount() > 0)) {
            return;
        }

        View firstChild = imageContainer.getChildAt(0);
        if (defaultAnimationType == SVProgressHUDAnimationType.Flat && firstChild != indefiniteAnimatedView) {
            imageContainer.removeAllViews();
            imageContainer.addView(indefiniteAnimatedView);
        } else if (defaultAnimationType == SVProgressHUDAnimationType.Native && firstChild != activityIndicatorView) {
            imageContainer.removeAllViews();
            imageContainer.addView(activityIndicatorView);
        }
    }

    //+ (void)setContainerView:(nullable UIView*)containerView;               // default is window level
    public void setMinimumSize(float width, float height) {
        this.minimumWidth = width;
        this.minimumHeight = height;

        this.hudView.setMinimumWidth(Utils.dp2px(activity, this.minimumWidth));
        this.hudView.setMinimumHeight(Utils.dp2px(activity, this.minimumHeight));
    }

    public void setRingThickness(float ringThickness) {
        this.ringThickness = ringThickness;
        indefiniteAnimatedView.setStrokeThickness(this.ringThickness);
        progressAnimatedView.setStrokeThickness(this.ringThickness);
    }

    public void setRingRadius(float radius) {
        this.ringRadius = radius;
        if (statusLabel.getText() != null && statusLabel.getText().length() > 0) {
            indefiniteAnimatedView.setRadius(ringRadius);
            progressAnimatedView.setRadius(ringRadius);
        }
    }

    public void setRingNoTextRadius(float radius) {
        this.ringNoTextRadius = radius;
        if (statusLabel.getText() == null || statusLabel.getText().length() == 0) {
            indefiniteAnimatedView.setRadius(ringNoTextRadius);
            progressAnimatedView.setRadius(ringNoTextRadius);
        }
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
        hudViewBackgroundDrawable.setCornerRadius(Utils.dp2px(activity, this.cornerRadius));
    }

    public void setBorderColor(int color) {
        this.borderColor = color;
        this.hudViewBackgroundDrawable.setStroke(Utils.dp2px(activity, borderWidth), borderColor);
    }

    public void setBorderWidth(float width) {
        this.borderWidth = width;
        this.hudViewBackgroundDrawable.setStroke(Utils.dp2px(activity, borderWidth), borderColor);
    }

    //+ (void)setFont:(nonnull UIFont*)font;                                  // default is [UIFont preferredFontForTextStyle:UIFontTextStyleSubheadline]
    public void setForegroundColor(int color) {
        this.foregroundColor = color;
    }

    public void setForegroundImageColor(int color) {
        this.foregroundImageColor = color;
    }

    public void setBackgroundColor(int color) {
        this.backgroundColor = color;
    }

    //+ (void)setHudViewCustomBlurEffect:(nullable UIBlurEffect*)blurEffect;  // default is nil, only used for SVProgressHUDStyleCustom, can be combined with backgroundColor

    public void setBackgroundLayerColor(int color) {
        this.backgroundLayerColor = color;
    }

    public void setImageViewSize(float width, float height) {
        this.imageViewWidth = width;
        this.imageViewHeight = height;

        this.infoImage.setMinimumWidth(Utils.dp2px(activity, imageViewWidth));
        this.infoImage.setMinimumHeight(Utils.dp2px(activity, imageViewHeight));
        this.successImage.setMinimumWidth(Utils.dp2px(activity, imageViewWidth));
        this.successImage.setMinimumHeight(Utils.dp2px(activity, imageViewHeight));
        this.errorImage.setMinimumWidth(Utils.dp2px(activity, imageViewWidth));
        this.errorImage.setMinimumHeight(Utils.dp2px(activity, imageViewHeight));
    }

    public void setShouldTintImages(boolean shouldTintImages) {
        this.shouldTintImages = shouldTintImages;
    }

    //+ (void)setInfoImage:(nonnull UIImage*)image;                           // default is the bundled info image provided by Freepik
    //+ (void)setSuccessImage:(nonnull UIImage*)image;                        // default is the bundled success image provided by Freepik
    //+ (void)setErrorImage:(nonnull UIImage*)image;                          // default is the bundled error image provided by Freepik
    //+ (void)setViewForExtension:(nonnull UIView*)view;                      // default is nil, only used if #define SV_APP_EXTENSIONS is set
    //+ (void)setGraceTimeInterval:(NSTimeInterval)interval;                  // default is 0 seconds

    public void setMinimumDismissTimeInterval(float interval) {
        this.minimumDismissTimeInterval = interval;
    }

    public void setMaximumDismissTimeInterval(float interval) {
        this.maximumDismissTimeInterval = interval;
    }

    public void setFadeInAnimationDuration(float duration) {
        this.fadeInAnimationDuration = duration;

        animBackgroundLayerFadeIn.setDuration((int) (fadeInAnimationDuration * 1000));
        animHudViewFadeIn.setDuration((int) (fadeInAnimationDuration * 1000));
    }

    public void setFadeOutAnimationDuration(float duration) {
        this.fadeOutAnimationDuration = duration;

        animBackgroundLayerFadeOut.setDuration((int) (fadeInAnimationDuration * 1000));
        animHudViewFadeOut.setDuration((int) (fadeInAnimationDuration * 1000));
    }

    //+ (void)setMaxSupportedWindowLevel:(UIWindowLevel)windowLevel;          // default is UIWindowLevelNormal

    public void setHapticsEnabled(boolean hapticsEnabled) {
        this.hapticsEnabled = hapticsEnabled;
    }

    public void setMotionEffectEnabled(boolean motionEffectEnabled) {
        this.motionEffectEnabled = motionEffectEnabled;
    }

    // Show Methods
    private void show(
            String status,
            ImageView imageView,
            float progress
    ) {
        imageContainer.removeAllViews();
        if (imageView != null) {
            imageContainer.addView(imageView);
        } else if (progress != -1) {
            progressAnimatedView.setProgress(progress);
            progressAnimatedView.setRadius(status != null ? this.ringRadius : this.ringNoTextRadius);
            imageContainer.addView(progressAnimatedView);
        } else {
            indefiniteAnimatedView.setRadius(status != null ? this.ringRadius : this.ringNoTextRadius);
            imageContainer.addView(defaultAnimationType == SVProgressHUDAnimationType.Flat ? indefiniteAnimatedView : activityIndicatorView);
        }

        if (imageView != null) {
            dismissWithDelay(minimumDismissTimeInterval);
        }

        statusLabel.setText(status);
        statusLabel.setVisibility(status != null ? View.VISIBLE : View.GONE);

        if (contentView.getParent() == null) {
            decorView.addView(contentView);
            backgroundLayer.startAnimation(animBackgroundLayerFadeIn);
            backgroundGradientLayer.startAnimation(animBackgroundLayerFadeIn);
            hudView.startAnimation(animHudViewFadeIn);
        }
    }

    public void show() {
        this.show(null, null, -1);
    }

    public void showWithStatus(String status) {
        this.show(status, null, -1);
    }

    public void showProgress(float progress) {
        this.show(null, null, progress);
    }

    public void showProgress(float progress, String status) {
        this.show(status, null, progress);
    }

    // change the HUD loading status while it's showing
    public void setStatus(String status) {
        statusLabel.setText(status);
    }

    //// stops the activity indicator, shows a glyph + status, and dismisses the HUD a little bit later
    public void showInfoWithStatus(String status) {
        this.show(status, infoImage, -1);
    }

    public void showSuccessWithStatus(String status) {
        this.show(status, successImage, -1);
    }

    public void showErrorWithStatus(String status) {
        this.show(status, errorImage, -1);
    }

    //// shows a image + status, use white PNGs with the imageViewSize (default is 28x28 pt)
    //+ (void)showImage:(nonnull UIImage*)image status:(nullable NSString*)status;

    //+ (void)setOffsetFromCenter:(UIOffset)offset;
    //+ (void)resetOffsetFromCenter;

    //+ (void)popActivity; // decrease activity count, if activity count == 0 the HUD is dismissed

    public void dismiss() {
        dismissWithDelay(0, null);
    }

    public void dismissWithCompletion(SVProgressHUDDismissCompletion completion) {
        dismissWithDelay(0, completion);
    }

    public void dismissWithDelay(float delay) {
        dismissWithDelay(delay, null);
    }

    public void dismissWithDelay(float delay, final SVProgressHUDDismissCompletion completion) {
        if (dismissAction != null) {
            dismissHandler.removeCallbacks(dismissAction);
        }

        dismissAction = () -> {
            backgroundLayer.startAnimation(animBackgroundLayerFadeOut);
            backgroundGradientLayer.startAnimation(animBackgroundLayerFadeOut);
            hudView.startAnimation(animHudViewFadeOut);
            animHudViewFadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (contentView.getParent() != null) {
                        decorView.removeView(contentView);
                    }

                    if (completion != null) {
                        completion.onCompletion();
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        };

        dismissHandler.postDelayed(dismissAction, (int) delay * 1000);
    }

    public boolean isVisible() {
        return contentView.getParent() != null;
    }

    //+ (NSTimeInterval)displayDurationForString:(nullable NSString*)string;
}

