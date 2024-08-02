plugins {
    kotlin("multiplatform") version "2.0.20-RC"   // compilation exception
//    kotlin("multiplatform") version "1.9.24"  // works fine
}

val isLinux = System.getProperty("os.name") == "Linux"
val isMac = System.getProperty("os.name") == "Mac OS X"

kotlin {
    jvmToolchain(11)
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    mingwX64()
    linuxX64()
    macosX64()
    macosArm64()

    sourceSets {
        val commonMain by getting {}
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("io.kotest:kotest-assertions-core:5.9.0")
            }
        }
    }
}
