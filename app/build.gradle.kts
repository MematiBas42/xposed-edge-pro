plugins {
    id("com.android.application")
}

android {
    namespace = "com.jozein.xedgepro"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jozein.xedgepro"
        minSdk = 19
        targetSdk = 31
        versionCode = 82
        versionName = "8.0.1"
    }

    signingConfigs {
        create("release") {
            val envKeystore = System.getenv("KEYSTORE_FILE")?.let { file(it) }
            val localKeystore = file("${rootDir}/keystore/release.jks").takeIf { it.exists() }
            val resolvedFile = envKeystore ?: localKeystore

            if (resolvedFile != null && resolvedFile.exists()) {
                storeFile = resolvedFile
                storePassword = System.getenv("KEYSTORE_PASSWORD") ?: "xedgepro"
                keyAlias = System.getenv("KEY_ALIAS") ?: "xedgepro"
                keyPassword = System.getenv("KEY_PASSWORD") ?: (System.getenv("KEYSTORE_PASSWORD") ?: "xedgepro")
            }
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
            val releaseSigning = signingConfigs.getByName("release")
            signingConfig = if (releaseSigning.storeFile != null) releaseSigning else signingConfigs.getByName("debug")
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
