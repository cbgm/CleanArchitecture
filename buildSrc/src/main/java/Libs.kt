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
      const val firebaseMessaging = "com.google.firebase:firebase-messaging:${Versions.firebaseMessagingVersion}"
      const val firebaseCore = "com.google.firebase:firebase-core:${Versions.firebaseCoreVersion}"
      const val firebaseDynamic = "com.google.firebase:firebase-dynamic-links:${Versions.firebaseDynamicVersion}"
      const val firebaseInAppMessaging = "com.google.firebase:firebase-inappmessaging-display:${Versions.firebaseInAppVersion}"
      const val firebaseAuth = "com.google.firebase:firebase-auth:${Versions.firebaseAuthVersion}"
      const val roomCore = "android.arch.persistence.room:runtime:${Versions.roomVersion}"
      const val roomCompiler = "android.arch.persistence.room:compiler:${Versions.roomVersion}"
      const val roomCoroutines = "androidx.room:room-coroutines:${Versions.roomVersion}"
      const val firebaseJob = "com.firebase:firebase-jobdispatcher:${Versions.firebaseJobVersion}"
   }

   object Test {
      const val junit = "junit:junit:${Versions.junitVersion}"
      const val testRunner = "androidx.test:runner:${Versions.testRunnerVersion}"
      const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
      const val koinTest = "org.koin:koin-test:${Versions.koinVersion}"
      const val roomTest = "android.arch.persistence.room:testing:${Versions.roomVersion}"
   }
}