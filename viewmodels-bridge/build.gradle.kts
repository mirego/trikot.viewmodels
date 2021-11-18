import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency

plugins {
    kotlin("multiplatform")
    kotlin("kapt")
    id("com.android.library")
    id("org.jlleitschuh.gradle.ktlint")
    id("mirego.release").version("2.0")
    id("mirego.publish").version("1.0")
}

repositories {
    google()
    mavenLocal()
    mavenCentral()
    maven("https://kotlin.bintray.com/kotlinx")
    maven("https://jitpack.io")
    maven("https://plugins.gradle.org/m2/")
    maven("https://s3.amazonaws.com/mirego-maven/public")
}

group = "com.mirego.trikot"

kotlin {
    android {
        publishAllLibraryVariants()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.mirego.trikot:trikotFoundation:${project.extra["trikot_foundation_version"]}")
                implementation("com.mirego.trikot:streams:${project.extra["trikot_streams_version"]}")
                implementation("com.mirego.trikot:viewmodels:${project.extra["trikot_viewmodels_version"]}")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test-common")
                implementation("org.jetbrains.kotlin:kotlin-test-annotations-common")
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("androidx.lifecycle:lifecycle-process:${project.extra["androidx_lifecycle_version"]}")
                implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
                implementation("androidx.recyclerview:recyclerview:1.2.1")
                implementation("androidx.appcompat:appcompat:1.4.0")
                implementation("com.squareup.picasso:picasso:2.71828")
                implementation("com.google.android.material:material:1.4.0")
                implementation("javax.annotation:javax.annotation-api:1.3.2")
            }
        }

        val androidTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
                implementation("androidx.test:core:1.4.0")
                implementation("androidx.test.ext:junit:1.1.3")
                implementation("org.robolectric:robolectric:4.5.1")
                implementation("androidx.fragment:fragment-testing:1.4.0")
            }
        }
    }
}

dependencies {
    configurations.get("kapt").dependencies.add(
        DefaultExternalModuleDependency(
            "com.android.databinding",
            "compiler",
            "3.1.4"
        )
    )
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

    sourceSets {
        val test by getting {
            manifest {
                srcFile("src/androidTest/AndroidManifest.xml")
            }
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

release {
    checkTasks = listOf("build", "check")
    buildTasks = listOf("publish")
    updateVersionPart = 2
}
