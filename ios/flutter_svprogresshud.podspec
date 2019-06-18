#
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html
#
Pod::Spec.new do |s|
  s.name             = 'flutter_svprogresshud'
  s.version          = '0.0.1'
  s.summary          = 'A clean and lightweight progress HUD for flutter app.'
  s.description      = <<-DESC
A new flutter plugin project.
                       DESC
  s.homepage         = 'https://github.com/blankapp/flutter_svprogresshud'
  s.license          = { :file => '../LICENSE' }
  s.author           = { 'JianyingLi' => 'lijy91@foxmail.com' }
  s.source           = { :path => '.' }
  s.source_files = 'Classes/**/*'
  s.public_header_files = 'Classes/**/*.h'
  s.dependency 'Flutter'
  s.dependency 'SVProgressHUD'

  s.ios.deployment_target = '8.0'
end

