#
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html.
# Run `pod lib lint flutter_svprogresshud.podspec' to validate before publishing.
#
Pod::Spec.new do |s|
  s.name             = 'flutter_svprogresshud'
  s.version          = '0.0.1'
  s.summary          = 'A clean and lightweight progress HUD for flutter app.'
  s.description      = <<-DESC
A new flutter plugin project.
                       DESC
  s.homepage         = 'https://github.com/leanflutter/flutter_svprogresshud'
  s.license          = { :file => '../LICENSE' }
  s.author           = { 'JianyingLi' => 'lijy91@foxmail.com' }
  s.source           = { :path => '.' }
  s.source_files = 'Classes/**/*'
  s.public_header_files = 'Classes/**/*.h'
  s.dependency 'Flutter'
  s.dependency 'SVProgressHUD'

  s.platform = :ios, '8.0'

  # Flutter.framework does not contain a i386 slice. Only x86_64 simulators are supported.
  s.pod_target_xcconfig = { 'DEFINES_MODULE' => 'YES', 'VALID_ARCHS[sdk=iphonesimulator*]' => 'x86_64' }
end
