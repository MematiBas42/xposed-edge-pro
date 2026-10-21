plugins {
    id("com.android.application")
}

android {
    namespace = "com.jozein.xedgepro"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jozein.xedgepro"
        minSdk = 21
        targetSdk = 31
        versionCode = 82
        versionName = "8.0.1"
    }

    signingConfigs {
        create("release") {
            storeFile = file("${rootDir}/keystore/release.jks")
            storePassword = "xedgepro"
            keyAlias = "xedgepro"
            keyPassword = "xedgepro"
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }

    lint {
        disable += "ExpiredTargetSdkVersion"
    }
}

dependencies {
    compileOnly(files("libs/xposed-api-82.jar"))
    testImplementation(files("libs/xposed-api-82.jar"))
    testImplementation("junit:junit:4.13.2")
}
