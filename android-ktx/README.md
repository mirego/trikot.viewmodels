# Trikot.viewmodels Android Kotlin Extensions

### Installation
Add dependency
```groovy
dependencies {
    implementation "com.mirego.trikot.viewModels:android-ktx:$trikot_viewmodels_android_ktx_version"
}
```

If class conflict arrives in your Android application. Add the following configuration in your Android build.gradle.

```groovy
configurations.all {
    exclude module: "viewModels-jvm"
}
```