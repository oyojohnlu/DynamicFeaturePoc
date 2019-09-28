plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinAndroidExtensions)
}

android {
    compileSdkVersion(Android.compile)
    defaultConfig {
        applicationId = "com.oyo.mobile.dynamicfeaturepoc"
        minSdkVersion(Android.min)
        versionCode = appVersionCode
        versionName = appVersionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    dynamicFeatures = mutableSetOf(":features:passport")
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.kotlinReflection)
    api(Libraries.appCompat)
    api(Libraries.ktxCore)
    api(Libraries.constraintLayout)
    api(Libraries.playCore)
    api(Libraries.koin)
    api(Libraries.koinViewmodel)
    testImplementation(TestLibraries.junit4)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
}
