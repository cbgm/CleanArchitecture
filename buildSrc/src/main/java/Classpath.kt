object Classpath{
   const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
   const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
   const val androidApplication = "com.android.application"
   const val androidLibraray = "com.android.library"
   const val androidFeature = "com.android.dynamic-feature"
   const val sonarPlugin = "org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:${Versions.sonarVersion}"
   const val kotlinAndroid = "kotlin-android"
   const val sonar = "org.sonarqube"
   const val kotlinAndroidExtensions = "kotlin-android-extensions"
   const val jacocoPlugin = "com.dicedmelon.gradle:jacoco-android:${Versions.jacocoVersion}"
   const val googlePlugin = "com.google.gms:google-services:${Versions.googleVersion}"
   const val hockeyPlugin =  "de.felixschulze.gradle:gradle-hockeyapp-plugin:${Versions.hockeyVersion}"
}