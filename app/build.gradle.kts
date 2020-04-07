plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(29)
    buildToolsVersion = "29.0.3"

    buildFeatures {
        dataBinding = true
    }

    kapt {
        generateStubs = true
    }

    defaultConfig {
        applicationId = "com.lifeindarkness.lifehacktask"
        minSdkVersion(19)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
            multiDexEnabled = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk8", "1.3.71"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.71")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.2.0")
    implementation("com.google.android.material:material:1.2.0-alpha05")
    implementation("androidx.constraintlayout:constraintlayout:2.0.0-beta4")
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.0.0")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("com.google.dagger:dagger:2.27")
    kapt("com.google.dagger:dagger-compiler:2.27")
    implementation("com.google.dagger:dagger-android:2.27")
    implementation("com.google.dagger:dagger-android-support:2.27")
    kapt("com.google.dagger:dagger-android-processor:2.27")
    kapt("com.android.databinding:compiler:3.1.4")
    implementation("com.squareup.retrofit2:retrofit:2.8.1")
    implementation("com.squareup.retrofit2:converter-gson:2.8.1")
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("com.squareup.okhttp3:okhttp:4.4.1")
    implementation("com.google.code.gson:gson:2.8.6")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.4")
    kapt("androidx.room:room-compiler:2.2.5")
    implementation("androidx.room:room-ktx:2.2.5")
    implementation("org.jetbrains.anko:anko:0.10.8")
    kapt("com.android.databinding:compiler:3.1.4")
}