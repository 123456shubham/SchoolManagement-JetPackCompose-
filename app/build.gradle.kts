plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("plugin.serialization") version "2.0.0-RC1"
    id("kotlin-kapt")  // <-- Add this line for kapt support
    id("dagger.hilt.android.plugin")


}

android {
    namespace = "com.example.school"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.school"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }


    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }


}

dependencies {


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
//    Navigation Dependency:
    implementation ("androidx.navigation:navigation-compose:2.8.4")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    debugImplementation("com.github.chuckerteam.chucker:library:3.5.2")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")



    // Dagger 2 for Dependency Injection
//    implementation("com.google.dagger:dagger:2.54")
//    kapt("com.google.dagger:dagger-compiler:2.54")
//    implementation("com.google.dagger:dagger-android:2.54")
//    implementation("com.google.dagger:dagger-android-support:2.54")
//    kapt("com.google.dagger:dagger-android-processor:2.54")

    // Hilt for dependency injection

    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")


    // Hilt for Compose
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")


    // for camera

//    implementation ("androidx.camera:camera-camera2:1.3.1")
//    implementation ("androidx.camera:camera-lifecycle:1.3.1")
//    implementation ("androidx.camera:camera-view:1.3.1")
//
//    implementation("com.github.bumptech.glide:glide:4.11.0")
//
//    implementation("com.github.bumptech.glide:glide:4.14.2")
//    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2")
//    implementation ("com.github.mhdmoh:swipe-button:1.0.3")

    // for image Picker
//    implementation("com.github.bumptech.glide:glide:4.14.2")
//    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2")

    // for retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    //okhttp
    implementation ("com.squareup.okhttp3:okhttp:4.9.1")
    //okhttp
    implementation ("org.jetbrains:annotations:15.0")

    implementation ("com.google.accompanist:accompanist-permissions:0.30.0")
    implementation ("io.coil-kt:coil-compose:2.3.0")



    // firebase

//    implementation(platform("com.google.firebase:firebase-bom:33.4.0"))
//    implementation("com.google.firebase:firebase-analytics")
//
//    implementation("com.google.firebase:firebase-crashlytics")

    // route listener dependency
//    implementation ("com.github.dangiashish:Google-Direction-Api:1.6") // Replace with the correct version


    // glide


//    implementation ("com.github.bumptech.glide:glide:4.14.2")
//    annotationProcessor ("com.github.bumptech.glide:compiler:4.14.2")

    // implement socket

//    implementation("io.socket:socket.io-client:2.1.0")


}

kapt {
    correctErrorTypes = true
}