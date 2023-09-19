import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
    id("java-library")
    id("com.palantir.consistent-versions").version("2.15.0")
    id("com.diffplug.spotless").version("6.21.0")
}

allprojects {
    repositories {
        mavenCentral()
    }

    val tidy by tasks.registering {
        doFirst {
            println("Tidy.")
        }
    }

    // This block is crucial - it creates a dependency somewhere in the task graph, which
    // leads to configuration resolution when the task graph is finalized.
    tasks.matching { task -> task.name == "spotlessApply" }.all {
        tidy.configure {
            dependsOn(this@all)
        }
    }

    extensions.configure<SpotlessExtension> {
        java {
            googleJavaFormat("1.17.0")
        }
    }
}