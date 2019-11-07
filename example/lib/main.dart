import 'package:flutter/foundation.dart'
    show debugDefaultTargetPlatformOverride;
import 'dart:async';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_svprogresshud/flutter_svprogresshud.dart';

/// If the current platform is desktop, override the default platform to
/// a supported platform (iOS for macOS, Android for Linux and Windows).
/// Otherwise, do nothing.
void _setTargetPlatformForDesktop() {
  TargetPlatform targetPlatform;
  try {
    if (Platform.isMacOS) {
      targetPlatform = TargetPlatform.iOS;
    } else if (Platform.isLinux || Platform.isWindows) {
      targetPlatform = TargetPlatform.android;
    }
  } catch (e) {
    targetPlatform = TargetPlatform.fuchsia;
  }

  if (targetPlatform != null) {
    debugDefaultTargetPlatformOverride = targetPlatform;
  }
}

void main() {
  _setTargetPlatformForDesktop();

  // SVProgressHUD.dismiss();
  // SVProgressHUD.setDefaultStyle("light");
  // SVProgressHUD.setDefaultMaskType("none");

  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Column(
          children: <Widget>[
            ListTile(
              title: Text('show'),
              onTap: () {
                int progress = 1;
                Timer.periodic(const Duration(milliseconds: 100), (timer) {
                  print(progress);
                  progress += 1;
                  if (progress > 100) {
                    timer.cancel();
                    SVProgressHUD.dismiss();
                    return;
                  }
                  SVProgressHUD.showProgress((progress), "Loading...");
                });
              },
            )
          ],
        ),
      ),
    );
  }
}
