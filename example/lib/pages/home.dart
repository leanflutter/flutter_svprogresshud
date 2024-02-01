import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svprogresshud/flutter_svprogresshud.dart';

class _ListSection extends StatelessWidget {
  const _ListSection({
    Key? key,
    this.title,
  }) : super(key: key);

  final Widget? title;

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.grey.withOpacity(0.1),
      padding: const EdgeInsets.only(
        left: 16,
        right: 16,
        top: 10,
        bottom: 10,
      ),
      child: Column(
        children: [
          Row(
            children: [
              DefaultTextStyle(
                style: const TextStyle(
                  color: Colors.black,
                  fontSize: 13,
                  fontWeight: FontWeight.bold,
                ),
                child: title!,
              ),
            ],
          ),
        ],
      ),
    );
  }
}

class _ListItem extends StatelessWidget {
  const _ListItem({
    Key? key,
    this.title,
    this.trailing,
    this.onTap,
  }) : super(key: key);

  final Widget? title;
  final Widget? trailing;
  final VoidCallback? onTap;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      behavior: HitTestBehavior.opaque,
      onTap: onTap,
      child: Container(
        constraints: const BoxConstraints(minHeight: 48),
        padding: const EdgeInsets.only(
          left: 16,
          right: 16,
          top: 8,
          bottom: 8,
        ),
        alignment: Alignment.centerLeft,
        child: Column(
          children: [
            Row(
              children: [
                DefaultTextStyle(
                  style: const TextStyle(
                    color: Colors.black,
                    fontSize: 15,
                  ),
                  child: title!,
                ),
                Expanded(child: Container()),
                if (trailing != null) SizedBox(height: 34, child: trailing),
              ],
            ),
          ],
        ),
      ),
    );
  }
}

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  Timer? _dismissAfter5sTimer;
  Timer? _progressTimer;

  SVProgressHUDStyle _style = SVProgressHUDStyle.light;
  SVProgressHUDMaskType _maskType = SVProgressHUDMaskType.none;
  SVProgressHUDAnimationType _animationType = SVProgressHUDAnimationType.flat;
  double _minimumSizeWidth = 0;
  double _minimumSizeHeight = 0;
  num _ringThickness = 2;
  num _ringRadius = 18;
  num _ringNoTextRadius = 24;
  num _cornerRadius = 14;
  bool _hapticsEnabled = false;

  @override
  void initState() {
    SVProgressHUD.dismiss();
    _updateHUDConfig();
    super.initState();
  }

  void _stopAllTimer() {
    if (_dismissAfter5sTimer != null && _dismissAfter5sTimer!.isActive) {
      _dismissAfter5sTimer!.cancel();
    }
    if (_progressTimer != null && _progressTimer!.isActive) {
      _progressTimer?.cancel();
    }
  }

  void _updateHUDConfig() {
    SVProgressHUD.setDefaultStyle(_style);
    SVProgressHUD.setDefaultMaskType(_maskType);
    SVProgressHUD.setDefaultAnimationType(_animationType);
    SVProgressHUD.setMinimumSize(Size(_minimumSizeWidth, _minimumSizeHeight));
    SVProgressHUD.setRingThickness(_ringThickness);
    SVProgressHUD.setRingRadius(_ringRadius);
    SVProgressHUD.setRingNoTextRadius(_ringNoTextRadius);
    SVProgressHUD.setCornerRadius(_cornerRadius);
    // SVProgressHUD.setBorderColor(Colors.red);
    // SVProgressHUD.setBorderWidth(12);
    // SVProgressHUD.setForegroundColor(Colors.red);
    // SVProgressHUD.setForegroundImageColor(Colors.amber);
    // SVProgressHUD.setBackgroundColor(Colors.lightGreen);
    // SVProgressHUD.setBackgroundLayerColor(Colors.cyan.withOpacity(0.5));
    // SVProgressHUD.setImageViewSize(Size(60, 60));
    // SVProgressHUD.setMinimumDismissTimeInterval(2);
    // SVProgressHUD.setFadeInAnimationDuration(1);
    // SVProgressHUD.setFadeOutAnimationDuration(1);
    SVProgressHUD.setHapticsEnabled(_hapticsEnabled);
  }

  void _dismissAfter5s() {
    if (_dismissAfter5sTimer != null && _dismissAfter5sTimer!.isActive) {
      _dismissAfter5sTimer!.cancel();
    }

    _dismissAfter5sTimer = Timer(const Duration(milliseconds: 5000), () {
      SVProgressHUD.dismiss();
    });
  }

  void _show() {
    _stopAllTimer();
    SVProgressHUD.show();
    _dismissAfter5s();
  }

  void _showWithStatus() {
    _stopAllTimer();
    SVProgressHUD.show(status: 'Doing Stuff');
    _dismissAfter5s();
  }

  void _showProgress() {
    _stopAllTimer();
    int progress = 1;
    _progressTimer = Timer.periodic(const Duration(milliseconds: 50), (timer) {
      progress += 1;
      if (progress >= 100) {
        timer.cancel();
        SVProgressHUD.dismiss();
        return;
      }
      SVProgressHUD.showProgress(progress / 100);
    });
  }

  void _showProgressWithStatus() {
    _stopAllTimer();
    int progress = 1;
    _progressTimer = Timer.periodic(const Duration(milliseconds: 50), (timer) {
      progress += 1;
      if (progress >= 100) {
        timer.cancel();
        SVProgressHUD.dismiss();
        return;
      }
      SVProgressHUD.showProgress(
        progress / 100,
        status: 'Loading...',
      );
    });
  }

  void _showInfoWithStatus() {
    _stopAllTimer();
    SVProgressHUD.showInfo(status: 'Useful Information.');
  }

  void _showSuccessWithStatus() {
    _stopAllTimer();
    SVProgressHUD.showSuccess(status: 'Great Success!');
  }

  void _showErrorWithStatus() {
    _stopAllTimer();
    SVProgressHUD.showError(status: 'Failed with Error');
  }

  void _dismiss() {
    _stopAllTimer();
    SVProgressHUD.dismiss(
      completion: () {
        if (kDebugMode) {
          print('HUD dismiss is completed!');
        }
      },
    );
  }

  void _dismissWithDelay() {
    _stopAllTimer();
    SVProgressHUD.dismiss(
      delay: const Duration(milliseconds: 1000),
      completion: () {
        if (kDebugMode) {
          print('HUD dismissWithDelay is completed!');
        }
      },
    );
  }

  Widget _buildSectionCustomization(BuildContext context) {
    return Column(
      children: <Widget>[
        const _ListSection(
          title: Text('Customization'),
        ),
        _ListItem(
          title: const Text('Style'),
          trailing: ToggleButtons(
            onPressed: (int index) {
              _style = SVProgressHUDStyle.values[index];
              _updateHUDConfig();

              setState(() {});
            },
            isSelected:
                SVProgressHUDStyle.values.map((e) => e == _style).toList(),
            children: <Widget>[
              for (var style in SVProgressHUDStyle.values) Text(style.name),
            ],
          ),
        ),
        const Divider(height: 0, indent: 16, endIndent: 16),
        _ListItem(
          title: const Text('AnimationType'),
          trailing: ToggleButtons(
            onPressed: (int index) {
              _animationType = SVProgressHUDAnimationType.values[index];
              _updateHUDConfig();

              setState(() {});
            },
            isSelected: SVProgressHUDAnimationType.values
                .map((e) => e == _animationType)
                .toList(),
            children: <Widget>[
              for (var animationType in SVProgressHUDAnimationType.values)
                Text(animationType.name),
            ],
          ),
        ),
        const Divider(height: 0, indent: 16, endIndent: 16),
        _ListItem(
          title: const Text('MaskType'),
          trailing: ToggleButtons(
            onPressed: (int index) {
              _maskType = SVProgressHUDMaskType.values[index];
              _updateHUDConfig();

              setState(() {});
            },
            isSelected: SVProgressHUDMaskType.values
                .map((e) => e == _maskType)
                .toList(),
            children: <Widget>[
              for (var maskType in SVProgressHUDMaskType.values)
                Text(maskType.name),
            ],
          ),
        ),
        const Divider(height: 0, indent: 16, endIndent: 16),
        _ListItem(
          title: const Text('MinimumSize'),
          trailing: SizedBox(
            width: 120,
            child: Row(
              children: [
                Expanded(
                  child: CupertinoTextField(
                    placeholder: '$_minimumSizeWidth',
                    keyboardType: TextInputType.number,
                    onChanged: (newValue) {
                      if (newValue.isEmpty) return;
                      _minimumSizeWidth = double.parse(newValue);
                      _updateHUDConfig();

                      setState(() {});
                    },
                    textAlign: TextAlign.right,
                  ),
                ),
                Expanded(
                  child: CupertinoTextField(
                    placeholder: '$_minimumSizeHeight',
                    keyboardType: TextInputType.number,
                    onChanged: (newValue) {
                      if (newValue.isEmpty) return;
                      _minimumSizeHeight = double.parse(newValue);
                      _updateHUDConfig();

                      setState(() {});
                    },
                    textAlign: TextAlign.right,
                  ),
                ),
              ],
            ),
          ),
        ),
        const Divider(height: 0, indent: 16, endIndent: 16),
        _ListItem(
          title: const Text('RingThickness'),
          trailing: SizedBox(
            width: 60,
            child: CupertinoTextField(
              placeholder: '$_ringThickness',
              keyboardType: TextInputType.number,
              onChanged: (newValue) {
                if (newValue.isEmpty) return;
                _ringThickness = num.parse(newValue);
                _updateHUDConfig();

                setState(() {});
              },
              textAlign: TextAlign.right,
            ),
          ),
        ),
        const Divider(height: 0, indent: 16, endIndent: 16),
        _ListItem(
          title: const Text('RingRadius'),
          trailing: SizedBox(
            width: 60,
            child: CupertinoTextField(
              placeholder: '$_ringRadius',
              keyboardType: TextInputType.number,
              onChanged: (newValue) {
                if (newValue.isEmpty) return;
                _ringRadius = num.parse(newValue);
                _updateHUDConfig();

                setState(() {});
              },
              textAlign: TextAlign.right,
            ),
          ),
        ),
        const Divider(height: 0, indent: 16, endIndent: 16),
        _ListItem(
          title: const Text('RingNoTextRadius'),
          trailing: SizedBox(
            width: 60,
            child: CupertinoTextField(
              placeholder: '$_ringNoTextRadius',
              keyboardType: TextInputType.number,
              onChanged: (newValue) {
                if (newValue.isEmpty) return;
                _ringNoTextRadius = num.parse(newValue);
                _updateHUDConfig();

                setState(() {});
              },
              textAlign: TextAlign.right,
            ),
          ),
        ),
        const Divider(height: 0, indent: 16, endIndent: 16),
        _ListItem(
          title: const Text('CornerRadius'),
          trailing: SizedBox(
            width: 60,
            child: CupertinoTextField(
              placeholder: '$_cornerRadius',
              keyboardType: TextInputType.number,
              onChanged: (newValue) {
                if (newValue.isEmpty) return;
                _cornerRadius = num.parse(newValue);
                _updateHUDConfig();

                setState(() {});
              },
              textAlign: TextAlign.right,
            ),
          ),
        ),
        const Divider(height: 0, indent: 16, endIndent: 16),
        _ListItem(
          title: const Text('HapticsEnabled'),
          trailing: Checkbox(
            value: _hapticsEnabled,
            onChanged: (newValue) {
              _hapticsEnabled = newValue!;
              _updateHUDConfig();

              setState(() {});
            },
          ),
        ),
      ],
    );
  }

  Widget _buildSectionExamples(BuildContext context) {
    return Column(
      children: <Widget>[
        const _ListSection(
          title: Text('Examples'),
        ),
        _ListItem(
          title: const Text('show'),
          onTap: _show,
        ),
        const Divider(height: 0, indent: 16, endIndent: 16),
        _ListItem(
          title: const Text('showWithStatus'),
          onTap: _showWithStatus,
        ),
        const Divider(height: 0, indent: 16, endIndent: 16),
        _ListItem(
          title: const Text('showProgress'),
          onTap: _showProgress,
        ),
        const Divider(height: 0, indent: 16, endIndent: 16),
        _ListItem(
          title: const Text('showProgressWithStatus'),
          onTap: _showProgressWithStatus,
        ),
        const Divider(height: 0, indent: 16, endIndent: 16),
        _ListItem(
          title: const Text('showInfoWithStatus'),
          onTap: _showInfoWithStatus,
        ),
        const Divider(height: 0, indent: 16, endIndent: 16),
        _ListItem(
          title: const Text('showSuccessWithStatus'),
          onTap: _showSuccessWithStatus,
        ),
        const Divider(height: 0, indent: 16, endIndent: 16),
        _ListItem(
          title: const Text('showErrorWithStatus'),
          onTap: _showErrorWithStatus,
        ),
        const Divider(height: 0, indent: 16, endIndent: 16),
        _ListItem(
          title: const Text('dismiss'),
          onTap: _dismiss,
        ),
        const Divider(height: 0, indent: 16, endIndent: 16),
        _ListItem(
          title: const Text('dismissWithDelay (1s)'),
          onTap: _dismissWithDelay,
        ),
      ],
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('flutter_svprogresshud'),
        elevation: 0,
      ),
      body: GestureDetector(
        onTap: () {
          FocusScopeNode currentFocus = FocusScope.of(context);
          if (!currentFocus.hasPrimaryFocus) currentFocus.unfocus();
        },
        child: ListView(
          padding: EdgeInsets.only(
            bottom: MediaQuery.of(context).padding.bottom,
          ),
          children: [
            _buildSectionCustomization(context),
            _buildSectionExamples(context),
          ],
        ),
      ),
    );
  }
}
