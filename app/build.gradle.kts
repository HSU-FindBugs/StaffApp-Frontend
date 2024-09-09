plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.findbug"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.findbug"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    dataBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // ViewModel, LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // OkHttp
    implementation(libs.okhttp)
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
    implementation (libs.logging.interceptor)
    implementation (libs.okhttp.urlconnection)

    // Retrofit
    implementation(libs.retrofit)

    // gson
    implementation(libs.gson)
    implementation(libs.converter.gson)

    // Moshi
    implementation (libs.moshi)
    implementation (libs.moshi.kotlin)
    implementation (libs.converter.moshi)

    // Room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // DataStore
    implementation (libs.datastore.preferences)
    implementation (libs.androidx.datastore.core)
    implementation(libs.androidx.datastore.preferences)

    // Glide
    implementation(libs.glide)
    annotationProcessor (libs.glide.compiler)

    // core-splash
    implementation(libs.androidx.core.splashscreen)

    // Navigation
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)

    // ViewPager2
    implementation(libs.androidx.viewpager2)

    // Coroutines
    implementation(libs.kotlinx.coroutines)

    // WorkManger
    implementation (libs.androidx.work.runtime.ktx)

    // CameraX
    implementation (libs.androidx.camera.core)
    implementation (libs.androidx.camera.camera2)
    implementation (libs.androidx.camera.lifecycle)
    implementation (libs.androidx.camera.video)
    implementation (libs.androidx.camera.view)
    implementation (libs.androidx.camera.mlkit.vision)
    implementation (libs.androidx.camera.extensions)
}