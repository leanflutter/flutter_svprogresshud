import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import './svprogresshud_animationtype.dart';
import './svprogresshud_masktype.dart';
import './svprogresshud_style.dart';

const kChannelName = 'flutter_svprogresshud';

class SVProgressHUD {
  static const MethodChannel _channel = const MethodChannel(kChannelName);

  static void show({
    String? status,
  }) {
    Map<String, dynamic> arguments = {'status': status};

    _channel.invokeMethod('show', arguments);
  }

  static void showProgress(
    num progress, {
    String? status,
  }) {
    Map<String, dynamic> arguments = {
      'progress': progress,
      'status': status,
    };

    _channel.invokeMethod('showProgress', arguments);
  }

  static void dismiss({Duration? delay, VoidCallback? completion}) {
    Map<String, dynamic> arguments = {};
    if (delay != null) {
      arguments = {'delay': delay.inMilliseconds};
    }
    _channel.invokeMethod('dismiss', arguments).then((_) {
      if (completion != null) completion();
    });
  }

  static void showInfo({String? status}) {
    Map<String, dynamic> arguments = {'status': status};
    _channel.invokeMethod('showInfo', arguments);
  }

  static void showSuccess({String? status}) {
    Map<String, dynamic> arguments = {'status': status};
    _channel.invokeMethod('showSuccess', arguments);
  }

  static void showError({String? status}) {
    Map<String, dynamic> arguments = {'status': status};
    _channel.invokeMethod('showError', arguments);
  }

  static void showImage({String? status}) {}

  static void setDefaultStyle(SVProgressHUDStyle style) {
    Map<String, dynamic> arguments = {'style': style.name};
    _channel.invokeMethod('setDefaultStyle', arguments);
  }

  static void setDefaultMaskType(SVProgressHUDMaskType maskType) {
    Map<String, dynamic> arguments = {'maskType': maskType.name};
    _channel.invokeMethod('setDefaultMaskType', arguments);
  }

  static void setDefaultAnimationType(SVProgressHUDAnimationType type) {
    Map<String, dynamic> arguments = {'type': type.name};
    _channel.invokeMethod('setDefaultAnimationType', arguments);
  }

  // + (void)setContainerView:(UIView*)containerView;                    // default is window level

  static void setMinimumSize(Size minimumSize) {
    Map<String, dynamic> arguments = {
      'width': minimumSize.width,
      'height': minimumSize.height,
    };
    _channel.invokeMethod('setMinimumSize', arguments);
  }

  static void setRingThickness(num ringThickness) {
    Map<String, dynamic> arguments = {'ringThickness': ringThickness};
    _channel.invokeMethod('setRingThickness', arguments);
  }

  static void setRingRadius(num radius) {
    Map<String, dynamic> arguments = {'radius': radius};
    _channel.invokeMethod('setRingRadius', arguments);
  }

  static void setRingNoTextRadius(num radius) {
    Map<String, dynamic> arguments = {'radius': radius};
    _channel.invokeMethod('setRingNoTextRadius', arguments);
  }

  static void setCornerRadius(num cornerRadius) {
    Map<String, dynamic> arguments = {'cornerRadius': cornerRadius};
    _channel.invokeMethod('setCornerRadius', arguments);
  }

  static void setBorderColor(Color color) {
    Map<String, dynamic> arguments = {'color': color.value};
    _channel.invokeMethod('setBorderColor', arguments);
  }

  static void setBorderWidth(num width) {
    Map<String, dynamic> arguments = {'width': width};
    _channel.invokeMethod('setBorderWidth', arguments);
  }

  // + (void)setFont:(UIFont*)font;                                      // default is [UIFont preferredFontForTextStyle:UIFontTextStyleSubheadline]

  static void setForegroundColor(Color color) {
    Map<String, dynamic> arguments = {'color': color.value};
    _channel.invokeMethod('setForegroundColor', arguments);
  }

  static void setForegroundImageColor(Color color) {
    Map<String, dynamic> arguments = {'color': color.value};
    _channel.invokeMethod('setForegroundImageColor', arguments);
  }

  static void setBackgroundColor(Color color) {
    Map<String, dynamic> arguments = {'color': color.value};
    _channel.invokeMethod('setBackgroundColor', arguments);
  }

  static void setBackgroundLayerColor(Color color) {
    Map<String, dynamic> arguments = {'color': color.value};
    _channel.invokeMethod('setBackgroundLayerColor', arguments);
  }

  static void setImageViewSize(Size size) {
    Map<String, dynamic> arguments = {
      'width': size.width,
      'height': size.height,
    };
    _channel.invokeMethod('setImageViewSize', arguments);
  }

  // + (void)setShouldTintImages:(BOOL)shouldTintImages;                 // default is YES
  // + (void)setInfoImage:(UIImage*)image;                               // default is the bundled info image provided by Freepik
  // + (void)setSuccessImage:(UIImage*)image;                            // default is bundled success image from Freepik
  // + (void)setErrorImage:(UIImage*)image;                              // default is bundled error image from Freepik
  // + (void)setViewForExtension:(UIView*)view;                          // default is nil, only used if #define SV_APP_EXTENSIONS is set
  // + (void)setGraceTimeInterval:(NSTimeInterval)interval;              // default is 0 seconds

  static void setMinimumDismissTimeInterval(num interval) {
    Map<String, dynamic> arguments = {'interval': interval};
    _channel.invokeMethod('setMinimumDismissTimeInterval', arguments);
  }

  static void setMaximumDismissTimeInterval(num interval) {
    Map<String, dynamic> arguments = {'interval': interval};
    _channel.invokeMethod('setMaximumDismissTimeInterval', arguments);
  }

  static void setFadeInAnimationDuration(num duration) {
    Map<String, dynamic> arguments = {'duration': duration};
    _channel.invokeMethod('setFadeInAnimationDuration', arguments);
  }

  static void setFadeOutAnimationDuration(num duration) {
    Map<String, dynamic> arguments = {'duration': duration};
    _channel.invokeMethod('setFadeOutAnimationDuration', arguments);
  }

  // + (void)setMaxSupportedWindowLevel:(UIWindowLevel)windowLevel;      // default is UIWindowLevelNormal

  static void setHapticsEnabled(bool hapticsEnabled) {
    Map<String, dynamic> arguments = {'hapticsEnabled': hapticsEnabled};
    _channel.invokeMethod('setHapticsEnabled', arguments);
  }
}
