import 'dart:async';
import 'dart:io';

import 'package:flutter/services.dart';

class SVProgressHUD {
  static const MethodChannel _channel =
      const MethodChannel('flutter_svprogresshud');

  static Future<bool> setDefaultStyle(String style) async {
    if (!Platform.isIOS) return false;

    return await _channel.invokeMethod('setDefaultStyle', {
      'style': style,
    });
  }

  static Future<bool> setDefaultMaskType(String maskType) async {
    if (!Platform.isIOS) return false;

    return await _channel.invokeMethod('setDefaultMaskType', {
      'maskType': maskType,
    });
  }

  static Future<bool> show(String status) async {
    return await _channel.invokeMethod('show', {
      'status': status,
    });
  }

  static Future<bool> showInfo(String status) async {
    return await _channel.invokeMethod('showInfo', {
      'status': status,
    });
  }

  static Future<bool> showSuccess(String status) async {
    return await _channel.invokeMethod('showSuccess', {
      'status': status,
    });
  }

  static Future<bool> showError(String status) async {
    return await _channel.invokeMethod('showError', {
      'status': status,
    });
  }

  static Future<bool> showProgress(num  progress, String status) async {
    return await _channel.invokeMethod('showProgress', {
      'progress': progress,
      'status': status,
    });
  }

  static Future<bool> dismiss() async {
    return await _channel.invokeMethod('dismiss');
  }

  static Future<bool> dismissWithDelay(int delay) async {
    return await _channel.invokeMethod('dismissWithDelay', {
      'delay': delay,
    });
  }
}
