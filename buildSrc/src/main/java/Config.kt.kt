object Config {

   object Versions {
      const val compileSdkVersion = 28
      const val applicationId = "com.distribution.christian.cleantest"
      const val minSdkVersion = 23
      const val targetSdkVersion = 28
      const val versionCode = 1
      const val versionName = "1.0"
      const val kotlinVersion = "1.3.10"


      const val supportLibVersion = "1.0.0-beta01"
      const val constraintLayoutVersion = "1.1.2"
      const val lifecycleVersion = "2.0.0-beta01"
      const val retrofitVersion = "2.4.0"
      const val coroutinesAdapterVersion = "0.9.2"
      const val coroutinesAndroidVersion = "1.2.0-alpha"
      const val coroutinesCoreVersion = "1.1.0-alpha"
      const val junitVersion = "4.12"
      const val testRunnerVersion = "1.1.0-alpha4"
      const val koinVersion = "1.0.2"
      const val espressoVersion = "3.1.0-alpha4"
      const val shimmerVersion = "0.3.0"
      const val timberVersion = "4.7.1"
      const val firebaseMessagingVersion = "17.3.4"
      const val firebaseCoreVersion = "16.0.4"
      const val firebaseDynamicVersion = "16.1.5"
      const val firebaseInAppVersion = "17.0.0"
      const val firebaseAuthVersion = "16.1.0"
      const val googleCoreVersion = "1.3.4"
      const val glideVersion = "4.8.0"
      const val materialVersion = "1.0.0"
      const val roomVersion = "2.1.0-alpha04"
      const val googleMapsVersion = "11.8.0"
   }

   object Libs {
      object Presentation {
         const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
         const val appcompat = "androidx.appcompat:appcompat:${Versions.supportLibVersion}"
         const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
         const val supportLib = "androidx.legacy:legacy-support-v4:${Versions.supportLibVersion}"
         const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycleVersion}"
         const val googleCore = "com.google.android.play:core:${Versions.googleCoreVersion}"
         const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
         const val cardView = "androidx.cardview:cardview:${Versions.supportLibVersion}"
         const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.supportLibVersion}"
         const val supportDesign = "com.google.android.material:material:${Versions.supportLibVersion}"
         const val koinAndroid = "org.koin:koin-android:${Versions.kotlinVersion}"
         const val koinCore = "org.koin:koin-core:${Versions.koinVersion}"
         const val koinScope = "org.koin:koin-android-scope:${Versions.koinVersion}"
         const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmerVersion}"
         const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
         const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
         const val material = "com.google.android.material:material:${Versions.materialVersion}"
         const val googleMaps = "com.google.android.gms:play-services-maps:${Versions.googleMapsVersion}"

      }

      object Data {
         const val coroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutinesAdapterVersion}"
         const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroidVersion}"
         const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCoreVersion}"
         const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
         const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
         const val firebaseMessaging = "com.google.firebase:firebase-messaging:${Versions.firebaseMessagingVersion}"
         const val firebaseCore = "com.google.firebase:firebase-core:${Versions.firebaseCoreVersion}"
         const val firebaseDynamic = "com.google.firebase:firebase-dynamic-links:${Versions.firebaseDynamicVersion}"
         const val firebaseInAppMessaging = "com.google.firebase:firebase-inappmessaging-display:${Versions.firebaseInAppVersion}"
         const val firebaseAuth = "com.google.firebase:firebase-auth:${Versions.firebaseAuthVersion}"
         const val roomCore = "android.arch.persistence.room:runtime:${Versions.roomVersion}"
         const val roomCompiler = "android.arch.persistence.room:compiler:${Versions.roomVersion}"
         const val roomCoroutines = "androidx.room:room-coroutines:${Versions.roomVersion}"
      }

      object Test {
         const val junit = "junit:junit:${Versions.junitVersion}"
         const val testRunner = "androidx.test:runner:${Versions.testRunnerVersion}"
         const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
         const val koinTest = "org.koin:koin-test:${Versions.koinVersion}"
         const val roomTest = "android.arch.persistence.room:testing:${Versions.roomVersion}"
      }
   }

   object Navigation {
      const val shop = "ACTION_SHOP"
      const val events = "ACTION_EVENTS"
      const val stars = "ACTION_STARS"
      const val profile = "ACTION_PROFILE"
      const val auth = "ACTION_AUTH"
   }
}
