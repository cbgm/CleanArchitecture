# CleanArchitecture concept

## Currently (in dev) configured with:
- Pattern MVP
- Koin dependency injection
- Sonarqube using detekt
    - easy use with homebrew (install sonar)
    - command: "sonar console" to start
- Coordinator pattern for navigation https://github.com/cbgm/MultiModuleNav
- Multi modules
- App bundle dynamic feature
- Split gradle files
- SourceSets for release and debug
- Deep links ( adb shell am start -W -a android.intent.action.VIEW -d "http://cleantest.de://*" com.distribution.christian.cleantest.debug), navigation module from https://github.com/cbgm/MultiModuleNav
- Firebase
    - Cloud Messaging
    - InAppMessaging
- DayNight-Mode toggled by power saving option
- Facebook Shimmer loading
- Kotlin coroutines
- Retrofit for Api construction

    
