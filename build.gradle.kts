plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    `maven-publish`
    id("com.jfrog.artifactory")
    id("com.diffplug.spotless")
}

val composeVersion: String = findProperty("composeVersion") as? String ?: "1.1.0-rc01"

version = "0.0.12"
group = "com.keygenqt.modifier"

spotless {
    kotlin {
        target("**/*.kt")
        licenseHeaderFile("$buildDir/../LICENSE")
    }
}

publishing {
    publications {
        register("aar", MavenPublication::class) {
            groupId = group.toString()
            artifactId = project.name
            artifact("$buildDir/outputs/aar/modifier-ext-$version-debug.aar")
        }
    }
}

artifactory {
    setContextUrl("https://artifactory.keygenqt.com/artifactory")
    publish {
        repository {
            setRepoKey("open-source")
            setUsername(findProperty("arusername").toString())
            setPassword(findProperty("arpassword").toString())
        }
        defaults {
            publications("aar")
            setPublishArtifacts(true)
        }
    }
}

android {

    compileSdk = 30

    defaultConfig {
        minSdk = 23
        targetSdk = 31
        setProperty("archivesBaseName", "modifier-ext-$version")
    }
}

dependencies {
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
}