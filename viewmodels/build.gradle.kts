import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency

plugins {
    id("org.jetbrains.kotlin.multiplatform").version("1.6.0-M1-139")
    id("org.jetbrains.kotlin.kapt").version("1.6.0-M1-139")
    id("com.android.library")
    id("org.jlleitschuh.gradle.ktlint").version("10.1.0")
    id("mirego.release").version("2.0")
    id("mirego.publish").version("1.0")
}

repositories {
    google()
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://plugins.gradle.org/m2/")
    maven("https://s3.amazonaws.com/mirego-maven/public")
}

group = "com.mirego.trikot"

kotlin {
    android {
        publishAllLibraryVariants()
    }

    jvm()
    ios()
    iosArm32("iosArm32")
    tvos()
    watchos()
    macosX64()
    js(IR) {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.mirego.trikot:trikotFoundation:${project.extra["trikot_foundation_version"]}")
                implementation("com.mirego.trikot:streams:${project.extra["trikot_streams_version"]}")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test-common")
                implementation("org.jetbrains.kotlin:kotlin-test-annotations-common")
            }
        }

        val jvmMain by getting {
            dependsOn(commonMain)
        }

        val jvmTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test")
                implementation("org.jetbrains.kotlin:kotlin-test-junit")
            }
        }

        val jsMain by getting {
            dependsOn(commonMain)
        }

        val jsTest by getting {
            dependsOn(commonTest)
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test-js")
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("androidx.lifecycle:lifecycle-process:${project.extra["androidx_lifecycle_version"]}")
                implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
                implementation("androidx.recyclerview:recyclerview:1.1.0")
                implementation("androidx.appcompat:appcompat:1.2.0")
                implementation("com.squareup.picasso:picasso:2.71828")
                implementation("com.google.android.material:material:1.3.0")
                implementation("javax.annotation:javax.annotation-api:1.3.2")
            }
        }

        val androidTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
                implementation("androidx.test:core:1.3.0")
                implementation("androidx.test.ext:junit:1.1.2")
                implementation("org.robolectric:robolectric:4.5.1")
                implementation("androidx.fragment:fragment-testing:1.3.2")
            }
        }

        val nativeMain by creating {
            dependsOn(commonMain)
        }

        val iosMain by getting {
            dependsOn(nativeMain)
        }

        val iosArm32Main by getting {
            dependsOn(iosMain)
        }

        val iosArm64Main by getting {
            dependsOn(iosMain)
        }

        val iosX64Main by getting {
            dependsOn(iosMain)
        }

        val tvosMain by getting {
            dependsOn(nativeMain)
        }

        val tvosArm64Main by getting {
            dependsOn(tvosMain)
        }

        val tvosX64Main by getting {
            dependsOn(tvosMain)
        }

        val watchosMain by getting {
            dependsOn(nativeMain)
        }

        val watchos32Main by creating {
            dependsOn(watchosMain)
        }

        val watchosArm64Main by getting {
            dependsOn(watchosMain)
        }

        val watchosX64Main by getting {
            dependsOn(watchosMain)
        }

        val macosX64Main by getting {
            dependsOn(nativeMain)
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
