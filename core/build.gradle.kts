import org.gradle.internal.impldep.bsh.commands.dir

plugins {
    alias(libs.plugins.android.library)
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
    namespace = "com.jovan.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            buildConfigField("String", "Base_API", "\"https://api.github.com/\"")
            buildConfigField("String", "API_Key", "\"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\"")
        }
        release {
            buildConfigField("String", "BASE_API", "\"https://api.github.com/\"")
            buildConfigField("String", "API_Key", "\"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\"")
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
}

dependencies {
    implementation (libs.kotlin.stdlib.jdk7)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //    retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    //    coroutine
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    api(libs.androidx.lifecycle.livedata.ktx)


    //room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    implementation (libs.androidx.room.ktx)

//    dagger hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}