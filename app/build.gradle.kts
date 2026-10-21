plugins {
    id("com.android.application")
}

android {
    namespace = "com.jozein.xedgepro"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jozein.xedgepro"
        minSdk = 21
        targetSdk = 35
        versionCode = 801
        versionName = "8.0.1"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
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
}

dependencies {
    compileOnly(files("libs/xposed-api-82.jar"))
    testImplementation(files("libs/xposed-api-82.jar"))
    testImplementation("junit:junit:4.13.2")
}
