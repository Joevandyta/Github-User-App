plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")

}
apply {
    from("../shared_dependencies.gradle")
}
android {
    namespace = "com.jovan.github_user_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jovan.github_user_app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_API", "\"${System.getenv("Base_API") ?: "https://api.github.com/"}\"")
            buildConfigField("String", "API_KEY", "\"${System.getenv("API_Key") ?: "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"}\"")

            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            buildConfigField("String", "BASE_API", "\"${System.getenv("Base_API") ?: "https://api.github.com/"}\"")
            buildConfigField("String", "API_KEY", "\"${System.getenv("API_Key") ?: "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"}\"")

            isMinifyEnabled = true
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
    dynamicFeatures += setOf(":favoriteuser")
}

dependencies {
    implementation (project(":core"))
    implementation (libs.kotlin.stdlib.jdk7)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

//    fragment
    implementation(libs.androidx.fragment.ktx)

    implementation(libs.androidx.lifecycle.extensions)

//    Glide
    implementation(libs.glide)

    //datastore
    implementation(libs.androidx.datastore.preferences)

//    viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)


//    leak canary
    debugImplementation(libs.leakcanary.android)

}
