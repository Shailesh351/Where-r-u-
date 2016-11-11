# WHERE R U?? #
**WHERE R U??** is an Android app which lets you locate people in your contact list who are using this app.
**Note:** This app uses location of your device.
## Steps: ##

- [x] Store user Mob. No. at first launch of app. 
- [ ] Get the device location coordinates.
- [ ] Push location and time at which location was last updated to server(local server).
- [ ] Get coordinates of all user(identified by Mob No.) from server.
- [ ] Add Google map API feature.
- [ ] Make the app run in background.
- [ ] Place markers on the map at retrived locations.
- [ ] Add Get Direction feature.

**Note:** Initially all the data will be stored local server. Later app should use **Firebase**.

**Note: Volley** is preferred instead of AsyncTask Method.

## Gradle Script: ##

 ```
 dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:24.1.1'
    compile 'com.android.support:design:24.2.0'
    compile 'com.google.android.gms:play-services-location:9.4.0'
}
```

## Contributors: ##

1. [Tanishka Khatri](https://github.com/Tanishka1997)
2. [Shailesh Baldaniya](https://github.com/Shailesh351)
3. [Karan Juneja](https://github.com/karanjuneja1106)
