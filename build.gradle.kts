plugins {
    id("mirego.release").version("2.0")
    id("mirego.publish").version("1.0")
}

buildscript {
    repositories {
        google()
        mavenLocal()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${project.extra["kotlin_version"]}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${project.extra["kotlin_version"]}")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:10.2.0")
    }
}

repositories {
    google()
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

release {
    checkTasks = listOf(
        ":viewmodels:check",
        ":viewmodels-bridge:check"
    )
    buildTasks = listOf(
        ":viewmodels:publish",
        ":viewmodels-bridge:publish"
    )
    updateVersionPart = 2
}