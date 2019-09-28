const val kotlinVersion = "1.3.41"
const val appVersionName = "1.0.0"
const val appVersionCode = 1

object Plugins {
    object Versions {
        const val buildToolsVersion = "3.5.0"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val androidDynamicFeature = "com.android.dynamic-feature"
}

object Android {
    const val min = 21
    const val compile = 29
    const val target = compile
}

object Libraries {
    private object Versions {
        const val jetpack = "1.0.0-beta01"
        const val constraintLayout = "1.1.3"
        const val ktx = "1.1.0"
        const val play = "1.5.0"
        const val koin = "2.0.1"
        const val koinViewmodel = "2.0.1"
    }

    const val kotlinStdLib     = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val kotlinReflection = "org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion"
    const val appCompat        = "androidx.appcompat:appcompat:${Versions.jetpack}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val playCore         = "com.google.android.play:core:${Versions.play}"
    const val ktxCore          = "androidx.core:core-ktx:${Versions.ktx}"
    const val koin             = "org.koin:koin-android:${Versions.koin}"
    const val koinViewmodel   = "org.koin:koin-androidx-viewmodel:${Versions.koinViewmodel}"
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.12"
        const val testRunner = "1.2.0"
        const val espresso = "3.2.0"
    }
    const val junit4     = "junit:junit:${Versions.junit4}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso   = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

object DynamicFeature {

}