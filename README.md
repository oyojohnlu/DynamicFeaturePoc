## POC of Dynamic Feature Module

### Android App Bundle
https://developer.android.com/platform/technology/app-bundle
An Android App Bundle is a new upload format that includes all your app’s compiled code and resources, but defers APK generation and signing to Google Play.

### Dynamic Feature Module
These modules contain features and assets that you can decide not to include when users first download and install your app. Using the Play Core Library, your app can later request to download those modules as dynamic feature APKs, and, through Dynamic Delivery, Google Play serves only the code and resources for that module to the device.

- Base module - app
- Feature module = passport

### Test Android app bundle
```
$ ./test_aab.sh
```