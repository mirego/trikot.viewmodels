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
