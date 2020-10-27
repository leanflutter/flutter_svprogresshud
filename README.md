# flutter_svprogresshud

[![pub version][pub-image]][pub-url]

[pub-image]: https://img.shields.io/pub/v/flutter_svprogresshud.svg
[pub-url]: https://pub.dev/packages/flutter_svprogresshud

## Installation

Add this to your package's pubspec.yaml file:

```yaml
dependencies:
  flutter_svprogresshud: ^0.0.4
```

You can install packages from the command line:

```bash
$ flutter packages get
```

## Usage

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

### Showing the HUD

You can show the status of indeterminate tasks using one of the following:

```dart
SVProgressHUD.show();
SVProgressHUD.show(status: 'Loading...');
```

If you'd like the HUD to reflect the progress of a task, use one of these:

```dart
SVProgressHUD.showProgress(0.91);
SVProgressHUD.showProgress(0.91, status: 'Processing...');
```

### Dismissing the HUD

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
SVProgressHUD.showInfo(status: 'A info message');
SVProgressHUD.showSuccess(status: 'A success message');
SVProgressHUD.showError(status: 'A error message');
```

## Related Links

- https://github.com/SVProgressHUD/SVProgressHUD
- https://github.com/Kaopiz/KProgressHUD

## License

```
MIT License

Copyright (c) 2020 LiJianying <lijy91@foxmail.com>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
