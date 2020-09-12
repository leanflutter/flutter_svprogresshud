import 'package:flutter/foundation.dart'
    show debugDefaultTargetPlatformOverride;
import 'dart:async';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_svprogresshud/flutter_svprogresshud.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    SVProgressHUD.dismiss();
    SVProgressHUD.setDefaultStyle("light");
    SVProgressHUD.setDefaultMaskType("none");
    super.initState();
  }

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
                SVProgressHUD.show("Loading...");
                SVProgressHUD.dismissWithDelay(1500);
              },
            ),
            ListTile(
              title: Text('showSuccess'),
              onTap: () {
                SVProgressHUD.showSuccess("Success");
                SVProgressHUD.dismissWithDelay(1500);
              },
            ),
            ListTile(
              title: Text('showInfo'),
              onTap: () {
                SVProgressHUD.showInfo("Info");
                SVProgressHUD.dismissWithDelay(1500);
              },
            ),
            ListTile(
              title: Text('showError'),
              onTap: () {
                SVProgressHUD.showError("Error");
                SVProgressHUD.dismissWithDelay(1500);
              },
            ),
            ListTile(
              title: Text('showProgress'),
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
            ),
            ListTile(
              title: Text('dismiss'),
              onTap: () {
                SVProgressHUD.dismiss();
              },
            )
          ],
        ),
      ),
    );
  }
}
