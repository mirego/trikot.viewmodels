plugins {
    id("com.android.library")
    id("kotlin-platform-android")
    id("org.jlleitschuh.gradle.ktlint")
    id("maven-publish")
    id("kotlin-kapt")
    id("mirego.release").version("2.0")
    id("mirego.publish").version("1.0")
}

repositories {
    google()
    mavenLocal()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
    maven("https://s3.amazonaws.com/mirego-maven/public")
}

group = "com.mirego.trikot.viewmodels"

configurations.forEach { it.exclude("org.reactivestreams") }

dependencies {
    api(project(":viewmodels"))
    api("com.mirego.trikot:streams:${project.extra["trikot_streams_version"]}")
    api("com.mirego.trikot:trikotFoundation:${project.extra["trikot_foundation_version"]}")
    implementation("androidx.lifecycle:lifecycle-process:${project.extra["androidx_lifecycle_version"]}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${project.extra["androidx_lifecycle_version"]}")
    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:${project.extra["androidx_lifecycle_version"]}")
    implementation("com.google.android.gms:play-services-base:17.6.0")
    implementation("com.google.android.libraries.maps:maps:3.1.0-beta")
    implementation("com.google.maps.android:maps-v3-ktx:3.0.0")
    implementation("com.google.maps.android:maps-utils-v3-ktx:3.0.0")
    implementation("com.google.maps.android:android-maps-utils-v3:2.2.0")
}

android {
    defaultConfig {
        compileSdkVersion(30)
        minSdkVersion(21)
        targetSdkVersion(30)
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
}

release {
    checkTasks = listOf("check")
    buildTasks = listOf("publish")
    updateVersionPart = 2
    tagPrefix = "google-maps-ktx-"
}

tasks {
    val sourcesJar by registering(Jar::class) {
        from(android.sourceSets.getByName("main").java.srcDirs)
        archiveClassifier.set("sources")
    }

    artifacts {
        archives(sourcesJar)
    }
}

publishing {
    publications {
        create<MavenPublication>("googleMapsAar") {
            artifactId = "google-maps-ktx"
            artifact("$buildDir/outputs/aar/$artifactId-release.aar")
            artifact(tasks["sourcesJar"])
        }
    }
}
