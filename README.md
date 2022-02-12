# flutter_svprogresshud

[![pub version][pub-image]][pub-url] [![][discord-image]][discord-url]

[pub-image]: https://img.shields.io/pub/v/flutter_svprogresshud.svg
[pub-url]: https://pub.dev/packages/flutter_svprogresshud

[discord-image]: https://img.shields.io/discord/884679008049037342.svg
[discord-url]: https://discord.gg/zPa6EZ2jqb


A clean and lightweight progress HUD for flutter app, based on SVProgressHUD.


> The plugin supports almost all of [SVProgressHUD](https://github.com/SVProgressHUD/SVProgressHUD) APIs, for Android we have almost replicated SVProgressHUD effects ([View code](https://github.com/leanflutter/flutter_svprogresshud/tree/master/android/src/main/java/org/leanflutter/svprogresshud)).

---

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [flutter_svprogresshud](#flutter_svprogresshud)
  - [Quick Start](#quick-start)
    - [Installation](#installation)
    - [Usage](#usage)
      - [Showing the HUD](#showing-the-hud)
      - [Dismissing the HUD](#dismissing-the-hud)
    - [Customization](#customization)
  - [Related Links](#related-links)
  - [License](#license)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

![SVProgressHUD](screenshots/SVProgressHUD.gif)


## Quick Start

### Installation

Add this to your package's pubspec.yaml file:

```yaml
dependencies:
  flutter_svprogresshud: ^1.0.0
```

You can install packages from the command line:

```bash
$ flutter packages get
```

### Usage

(see demo in `/example`)

`SVProgressHUD` is created as a singleton (i.e. it doesn't need to be explicitly allocated and instantiated; you directly call `SVProgressHUD.method()`).

**Use `SVProgressHUD` wisely! Only use it if you absolutely need to perform a task before taking the user forward. Bad use case examples: pull to refresh, infinite scrolling, sending message.**

Using `SVProgressHUD` in your app will usually look as simple as this:

```dart
SVProgressHUD.show();
Future.delayed(Duration(seconds: 3)).then((value) {
  SVProgressHUD.dismiss();
});
```

#### Showing the HUD

You can show the status of indeterminate tasks using one of the following:

```dart
SVProgressHUD.show();
SVProgressHUD.show(status: 'Doing Stuff');
```

If you'd like the HUD to reflect the progress of a task, use one of these:

```dart
SVProgressHUD.showProgress(0.91);
SVProgressHUD.showProgress(0.91, status: 'Loading...');
```

#### Dismissing the HUD

The HUD can be dismissed using:

```dart
SVProgressHUD.dismiss()
SVProgressHUD.dismiss(delay: Duration(milliseconds: 2000));
```

If you'd like to stack HUDs, you can balance out every show call using:

```
+ (void)popActivity;
```

The HUD will get dismissed once the popActivity calls will match the number of show calls.

Or show a confirmation glyph before before getting dismissed a little bit later. The display time depends on `minimumDismissTimeInterval` and the length of the given string.

```dart
SVProgressHUD.showInfo(status: 'Useful Information.');
SVProgressHUD.showSuccess(status: 'Great Success!');
SVProgressHUD.showError(status: 'Failed with Error');
```

### Customization

`SVProgressHUD` can be customized via the following methods:

```dart
void setDefaultStyle(SVProgressHUDStyle style);                 // default is SVProgressHUDStyle.Light
void setDefaultMaskType(SVProgressHUDMaskType maskType);        // default is SVProgressHUDMaskType.None
void setDefaultAnimationType(SVProgressHUDAnimationType type);  // default is SVProgressHUDAnimationType.Flat
void setMinimumSize(Size minimumSize);                          // default is Size.zero, can be used to avoid resizing
void setRingThickness(num width);                               // default is 2 pt
void setRingRadiu(num radius);                                  // default is 18 pt
void setRingNoTextRadius(num radius);                           // default is 24 pt
void setCornerRadius(num cornerRadius);                         // default is 14 pt
void setBorderColor(Color color);                               // default is null
void setBorderWidth(num width);                                 // default is 0
void setForegroundColor(Color color);                           // default is Colors.black, only used for SVProgressHUDStyle.Custom
// void setForegroundImageColor(Color color);                      // default is the same as foregroundColor
void setBackgroundColor(Color color);                           // default is Colors.white, only used for SVProgressHUDStyle.Custom
void setBackgroundLayerColor(Color color);                      // default is [Color colorWithWhite:0 alpha:0.4], only used for SVProgressHUDMaskType.Custom
void setImageViewSize(Size size);                               // default is 28x28 pt
// void setShouldTintImages(bool shouldTintImages);                // default is true
// void setInfoImage(UIImage image);                               // default is the bundled info image provided by Freepik
// void setSuccessImage(UIImage image);                            // default is bundled success image from Freepik
// void setErrorImage(UIImage image);                              // default is bundled error image from Freepik
// void setGraceTimeInterval(num interval);                        // default is 0 seconds
void setMinimumDismissTimeInterval(num interval);               // default is 5.0 seconds
// void setMaximumDismissTimeInterval(num interval);               // default is CGFLOAT_MAX
void setFadeInAnimationDuration(num duration);                  // default is 0.15 seconds
void setFadeOutAnimationDuration(num duration);                 // default is 0.15 seconds
void setHapticsEnabled(bool hapticsEnabled);                    // default is false
```

## Related Links

- https://github.com/SVProgressHUD/SVProgressHUD

## License

[MIT](./LICENSE)
