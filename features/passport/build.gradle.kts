plugins {
    id(Plugins.androidDynamicFeature)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinAndroidExtensions)
}

android {
    compileSdkVersion(Android.compile)
    defaultConfig {
        minSdkVersion(Android.min)
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":app"))
}
