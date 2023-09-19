import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.spotless.LineEnding

plugins {
    id("java")
    id("com.palantir.consistent-versions").version("2.15.0")
    id("com.diffplug.spotless").version("6.21.0").apply(false)
}

allprojects {
    repositories {
        mavenCentral()
    }
}

allprojects {
    plugins.withType<JavaPlugin>() {
        apply(plugin = "com.diffplug.spotless")

        extensions.configure<SpotlessExtension> {
            java {
                lineEndings = LineEnding.UNIX
                endWithNewline()
                googleJavaFormat("1.17.1")
            }
        }
    }
}