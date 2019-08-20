import 'dart:async';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_svprogresshud/flutter_svprogresshud.dart';

void main() {
  // SVProgressHUD.setDefaultStyle("light");
  SVProgressHUD.setDefaultMaskType("none");
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {

    SVProgressHUD.dismiss();
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
                SVProgressHUD.show('Loading...');
                SVProgressHUD.dismissWithDelay(1500);
              },
            )
          ],
        ),
      ),
    );
  }
}
