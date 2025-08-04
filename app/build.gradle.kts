plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.surendra.corpusassignmenttask"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.surendra.corpusassignmenttask"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.fragment.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("androidx.core:core-splashscreen:1.0.1")



    // Material Design
    implementation ("com.google.android.material:material:1.12.0")

    // ViewModel & LiveData
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.2")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.9.2")

    // SplashScreen API
    implementation ("androidx.core:core-splashscreen:1.0.1")

    // ViewPager2 & RecyclerView
    implementation ("androidx.viewpager2:viewpager2:1.1.0")
    implementation ("androidx.recyclerview:recyclerview:1.4.0")

    // ConstraintLayout & CardView
    implementation ("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation ("androidx.cardview:cardview:1.0.0")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")

    // JSON parsing
    implementation ("com.google.code.gson:gson:2.13.1")

    // Glide (Optional for image loading)
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    //kapt "com.github.bumptech.glide:compiler:4.16.0"

}