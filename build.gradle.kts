//buildscript {
//    dependencies {
//        classpath ("com.android.tools.build:gradle:8.3.0")
//    }
//}
//// Top-level build file where you can add configuration options common to all sub-projects/modules.
//plugins {
//    id("com.android.application") version "8.2.2" apply false
//    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
//}

buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.0")
        classpath("com.google.gms:google-services:4.4.2")
    }






}

// Root-level build.gradle.kts
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("com.android.library") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false // Update to 2.0.0
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0" apply false // Add this line
    id("com.google.gms.google-services") version "4.4.0" apply false
}


