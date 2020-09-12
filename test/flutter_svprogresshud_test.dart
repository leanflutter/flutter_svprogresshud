import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
// import 'package:flutter_svprogresshud/flutter_svprogresshud.dart';

void main() {
  const MethodChannel channel = MethodChannel('flutter_svprogresshud');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  // test('getPlatformVersion', () async {
  //   expect(await FlutterSvprogresshud.platformVersion, '42');
  // });
}
