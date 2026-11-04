import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library")
}

android {
    namespace = "at.bitfire.vcard4android"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        // ez-vcard uses java.time
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    packaging {
        resources {
            excludes += setOf("LICENSE", "META-INF/LICENSE.txt", "META-INF/NOTICE.txt")
        }
    }

    buildFeatures {
        buildConfig = true
    }

    lint {
        // standalone library without version catalog (lint finds the app's catalog when built as submodule)
        disable += setOf("AllowBackup", "InvalidPackage", "UseTomlInstead")
    }
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.5")

    implementation("androidx.annotation:annotation:1.11.0")
    implementation("commons-io:commons-io:2.22.0")
    implementation("org.apache.commons:commons-lang3:3.21.0")
    implementation("org.apache.commons:commons-text:1.15.0")

    // ez-vcard to parse/generate vCards
    api("com.googlecode.ez-vcard:ez-vcard:0.12.2") {
        // hCard functionality not needed
        exclude(group = "org.jsoup")
        exclude(group = "org.freemarker")
        // jCard functionality not needed
        exclude(group = "com.fasterxml.jackson.core")
    }

    androidTestImplementation("androidx.test:runner:1.7.0")
    androidTestImplementation("androidx.test:rules:1.7.0")

    testImplementation("junit:junit:4.13.2")
    // VCard references freemarker in a catch clause, which the JVM (unlike Android) needs to verify the class
    testImplementation("org.freemarker:freemarker:2.3.35")
}
