import com.vanniktech.maven.publish.AndroidMultiVariantLibrary
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.SourcesJar

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.raung)
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(17)
}

android {
    enableKotlin = false

    namespace = "com.v7878.sun.cleaner"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 26
    }
}

dependencies {
}

mavenPublishing {
    publishToMavenCentral(automaticRelease = false)
    signAllPublications()
    configure(
        AndroidMultiVariantLibrary(
            javadocJar = JavadocJar.Empty(),
            sourcesJar = SourcesJar.Sources()
        )
    )

    coordinates(
        groupId = "io.github.vova7878",
        artifactId = "SunCleanerStub",
        version = project.version.toString()
    )

    pom {
        name.set("SunCleanerStub")
        description.set("Wrapper over sun.misc.Cleaner for Android")
        inceptionYear.set("2025")
        url.set("https://github.com/vova7878/SunCleanerStub")

        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/license/mit")
                distribution.set("repository")
            }
        }

        developers {
            developer {
                id.set("vova7878")
                name.set("Vladimir Kozelkov")
                url.set("https://github.com/vova7878")
            }
        }

        scm {
            url.set("https://github.com/vova7878/SunCleanerStub")
            connection.set("scm:git:git://github.com/vova7878/SunCleanerStub.git")
            developerConnection.set("scm:git:ssh://git@github.com/vova7878/SunCleanerStub.git")
        }
    }
}
