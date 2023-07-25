buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
    }
}
plugins {
    id ("com.android.application") version "7.4.0" apply false
    id ("com.android.library") version "7.4.0" apply false
    id ("org.jetbrains.kotlin.android") version "1.7.21" apply false
    id ("com.google.gms.google-services") version "4.3.15" apply false
}