// Gradle init script for cloud sessions: fetch Maven Central artifacts from Google's mirror instead of
// repo.maven.apache.org, which rate-limits the shared proxy (HTTP 429). Not part of the project build.
// Install:  mkdir -p ~/.gradle/init.d && cp .claude-work/gradle-mirror.init.gradle.kts ~/.gradle/init.d/
val mirror = uri("https://maven-central.storage-download.googleapis.com/maven2/")

fun RepositoryHandler.useMirror() =
    withType(MavenArtifactRepository::class.java).configureEach {
        if (url.host == "repo.maven.apache.org" || url.host == "repo1.maven.org") url = mirror
    }

beforeSettings {
    pluginManagement.repositories.useMirror()
    dependencyResolutionManagement.repositories.useMirror()
}

allprojects {
    buildscript.repositories.useMirror()
    repositories.useMirror()
}
