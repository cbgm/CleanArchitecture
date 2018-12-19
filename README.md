# CleanArchitecture concept

## Configured with:
- Koin dependency injection
- Sonarqube using detekt
    - easy use with homebrew (install sonar)
    - command: "sonar console" to start
- Coordinator pattern for navigation
- Multi modules
- Split gradle files
- SourceSets for release and debug
- Deep links (adb shell am start -W -a android.intent.action.VIEW -d  "cleantest.de://*"  com.example.christian.cleantest.debug)
- Firebase
    - Cloud Messaging
    - InAppMessaging
- DayNight-Mode toggled by power saving option
- Facebook Shimmer loading
- Kotlin coroutines

    
 Refer to branch koin_test until served to master
 
