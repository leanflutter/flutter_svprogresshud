import 'dart:async';

import 'package:flutter/services.dart';

class SVProgressHUD {
  static const MethodChannel _channel =
      const MethodChannel('flutter_svprogresshud');

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
    return await _channel.invokeMethod('showError', {
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
