buildscript {
    repositories {
        google()
        mavenLocal()
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
    }
}

repositories {
    google()
}

allprojects {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
    }
}
