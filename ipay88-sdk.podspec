Pod::Spec.new do |s|
  s.name         = "ipay88-sdk"
  s.version      = "1.0.9"
  s.summary      = "ipay88-sdk"
  s.description  = <<-DESC
                  RNIpay88Sdk
                   DESC
  s.homepage     = "https://github.com/virtualspirit/react-native-ipay88-sdk"
  s.license      = "MIT"
  s.author       = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/virtualspirit/react-native-ipay88-sdk.git", :tag => "master" }

  s.source_files = "ios/iPay.h", "ios/IpayPayment.h", "ios/RNIpay88Sdk.h", "ios/RNIpay88Sdk.m"
  # s.vendored_libraries = "ios/**/*.a"
  s.vendored_frameworks = "ios/**/*.xcframework"

  s.requires_arc = true

  s.dependency "React"
end
