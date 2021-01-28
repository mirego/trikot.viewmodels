Pod::Spec.new do |spec|
  spec.name          = "Trikot.viewmodels"
  spec.version       = "0.0.1"
  spec.summary       = "Plugin for trikot.viewmodels."
  spec.description   = "Plugin for trikot.viewmodels."
  spec.homepage      = "https://github.com/mirego/trikot.viewmodels"
  spec.license       = "MIT license"
  spec.author        = { "Martin Gagnon" => "mgagnon@mirego.com" }
  spec.source        = { :git => "https://github.com/mirego/trikot.viewmodels.git", :tag => "#{spec.version}" }
  spec.static_framework = true

  spec.dependency 'Trikot.streams'
  spec.dependency ENV['TRIKOT_FRAMEWORK_NAME']

  spec.default_subspec = 'viewmodels'

  spec.subspec 'viewmodels' do |vms|
   spec.source_files  = "swift-extensions/*.swift"
   spec.tvos.source_files = "swift-extensions/*.swift"
   spec.tvos.exclude_files = "swift-extensions/UISliderExtensions.swift"
  end

  spec.subspec 'Kingfisher' do |kf|
    kf.source_files = 'swift-extensions/kingfisher/*.swift'
    kf.dependency 'Kingfisher', '5.15.7'
    kf.ios.deployment_target = '10.0'
  end

  spec.prepare_command = <<-CMD
    sed -i '' "s/TRIKOT_FRAMEWORK_NAME/${TRIKOT_FRAMEWORK_NAME}/g" ./**/*.swift
    sed -i '' "s/TRIKOT_FRAMEWORK_NAME/${TRIKOT_FRAMEWORK_NAME}/g" ./**/**/*.swift
  CMD
end
