import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import './svprogresshud_animationtype.dart';
import './svprogresshud_masktype.dart';
import './svprogresshud_style.dart';

const kChannelName = 'flutter_svprogresshud';

class SVProgressHUD {
  static const MethodChannel _channel = const MethodChannel(kChannelName);

  static void show({
    String status,
  }) {
    Map<String, dynamic> arguments = {'status': status};

    _channel.invokeMethod('show', arguments);
  }

  static void showProgress(
    num progress, {
    String status,
  }) {
    Map<String, dynamic> arguments = {
      'progress': progress,
      'status': status,
    };

    _channel.invokeMethod('showProgress', arguments);
  }

  static void dismiss({Duration delay, VoidCallback completion}) {
    Map<String, dynamic> arguments = {};
    if (delay != null) {
      arguments = {'delay': delay?.inMilliseconds};
    }
    _channel.invokeMethod('dismiss', arguments).then((_) {
      if (completion != null) completion();
    });
  }

  static void showInfo({String status}) {
    Map<String, dynamic> arguments = {'status': status};
    _channel.invokeMethod('showInfo', arguments);
  }

  static void showSuccess({String status}) {
    Map<String, dynamic> arguments = {'status': status};
    _channel.invokeMethod('showSuccess', arguments);
  }

  static void showError({String status}) {
    Map<String, dynamic> arguments = {'status': status};
    _channel.invokeMethod('showError', arguments);
  }

  static void showImage({String status}) {}

  // default is SVProgressHUDStyleLight
  static void setDefaultStyle(SVProgressHUDStyle style) {
    Map<String, dynamic> arguments = {'style': style.name};
    _channel.invokeMethod('setDefaultStyle', arguments);
  }

  // default is SVProgressHUDMaskTypeNone
  static void setDefaultMaskType(SVProgressHUDMaskType maskType) {
    Map<String, dynamic> arguments = {'maskType': maskType.name};
    _channel.invokeMethod('setDefaultMaskType', arguments);
  }

  // default is SVProgressHUDAnimationTypeFlat
  static void setDefaultAnimationType(SVProgressHUDAnimationType type) {
    Map<String, dynamic> arguments = {'type': type.name};
    _channel.invokeMethod('setDefaultAnimationType', arguments);
  }

  // + (void)setContainerView:(UIView*)containerView;                    // default is window level

  // default is CGSizeZero, can be used to avoid resizing
  static void setMinimumSize(Size minimumSize) {
    Map<String, dynamic> arguments = {
      'width': minimumSize.width,
      'height': minimumSize.height,
    };
    _channel.invokeMethod('setMinimumSize', arguments);
  }

  // default is 2 pt
  static void setRingThickness(num ringThickness) {
    Map<String, dynamic> arguments = {'ringThickness': ringThickness};
    _channel.invokeMethod('setRingThickness', arguments);
  }

  // default is 18 pt
  static void setRingRadius(num radius) {
    Map<String, dynamic> arguments = {'radius': radius};
    _channel.invokeMethod('setRingRadius', arguments);
  }

  // default is 24 pt
  static void setRingNoTextRadius(num radius) {
    Map<String, dynamic> arguments = {'radius': radius};
    _channel.invokeMethod('setRingNoTextRadius', arguments);
  }

  // default is 14 pt
  static void setCornerRadius(num cornerRadius) {
    Map<String, dynamic> arguments = {'cornerRadius': cornerRadius};
    _channel.invokeMethod('setCornerRadius', arguments);
  }

  // + (void)setBorderColor:(nonnull UIColor*)color;                     // default is nil
  // + (void)setBorderWidth:(CGFloat)width;                              // default is 0
  // + (void)setFont:(UIFont*)font;                                      // default is [UIFont preferredFontForTextStyle:UIFontTextStyleSubheadline]
  // + (void)setForegroundColor:(UIColor*)color;                         // default is [UIColor blackColor], only used for SVProgressHUDStyleCustom
  // + (void)setForegroundImageColor:(nullable UIColor*)color;           // default is the same as foregroundColor
  // + (void)setBackgroundColor:(UIColor*)color;                         // default is [UIColor whiteColor], only used for SVProgressHUDStyleCustom
  // + (void)setBackgroundLayerColor:(UIColor*)color;                    // default is [UIColor colorWithWhite:0 alpha:0.4], only used for SVProgressHUDMaskTypeCustom
  // + (void)setImageViewSize:(CGSize)size;                              // default is 28x28 pt
  // + (void)setShouldTintImages:(BOOL)shouldTintImages;                 // default is YES
  // + (void)setInfoImage:(UIImage*)image;                               // default is the bundled info image provided by Freepik
  // + (void)setSuccessImage:(UIImage*)image;                            // default is bundled success image from Freepik
  // + (void)setErrorImage:(UIImage*)image;                              // default is bundled error image from Freepik
  // + (void)setViewForExtension:(UIView*)view;                          // default is nil, only used if #define SV_APP_EXTENSIONS is set
  // + (void)setGraceTimeInterval:(NSTimeInterval)interval;              // default is 0 seconds
  // + (void)setMinimumDismissTimeInterval:(NSTimeInterval)interval;     // default is 5.0 seconds
  // + (void)setMaximumDismissTimeInterval:(NSTimeInterval)interval;     // default is CGFLOAT_MAX
  // + (void)setFadeInAnimationDuration:(NSTimeInterval)duration;        // default is 0.15 seconds
  // + (void)setFadeOutAnimationDuration:(NSTimeInterval)duration;       // default is 0.15 seconds
  // + (void)setMaxSupportedWindowLevel:(UIWindowLevel)windowLevel;      // default is UIWindowLevelNormal
  // default is NO
  static void setHapticsEnabled(bool hapticsEnabled) {
    Map<String, dynamic> arguments = {'hapticsEnabled': hapticsEnabled};
    _channel.invokeMethod('setHapticsEnabled', arguments);
  }
}
