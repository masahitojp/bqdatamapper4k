/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 */
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    val kotlinVersion = "1.8.0"
    kotlin("jvm") version kotlinVersion
    id("org.jetbrains.dokka") version "1.7.20"
    `maven-publish`
    signing
}
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

group = "com.github.masahitojp"
version = "0.2.0"
val artifactID = "bqdatamapper4k"

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "1.8"

repositories {
    mavenCentral()
    maven (url ="https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    maven (url = "https://maven-central.storage.googleapis.com")
}

dependencies {

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("com.google.cloud:google-cloud-bigquery:2.13.8")
    implementation("com.google.code.gson:gson:2.9.0")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

// Configure existing Dokka task to output HTML to typical Javadoc directory
tasks.dokkaHtml.configure {
    outputDirectory.set(buildDir.resolve("javadoc"))
}

// Create dokka Jar task from dokka task output
val dokkaJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Kotlin docs with Dokka"
    archiveClassifier.set("javadoc")
    // dependsOn(dokka) not needed; dependency automatically inferred by from(dokka)
    from(tasks.dokkaHtml)
}

// Create sources Jar from main kotlin sources
val sourcesJar by tasks.creating(Jar::class) {
    description = "Assembles sources JAR"
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

artifacts {
    add("archives", sourcesJar)
    add("archives", dokkaJar)
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes(
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version,
            "Implementation-Vendor" to "masahito.me",
            "Built-JDK" to "${System.getProperty("java.version")} (${System.getProperty("java.specification.vendor")})",
            "Built-Gradle" to gradle.gradleVersion
        )
    }
}

val sonatypeUsername = project.findProperty("sonatypeUsername")?.toString() ?: ""
val sonatypePassword = project.findProperty("sonatypePassword")?.toString() ?: ""

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            from(components.findByName("kotlin"))
            artifact(sourcesJar)
            artifact(dokkaJar)
            pom {
                name.set(artifactId)
                description.set("BigQuery datamapper for Kotlin")
                url.set("https://github.com/masahitojp/bqdatamapper4k")
                licenses {
                    license {
                        name.set("Apache License, Version 2.0")
                        url.set("https://opensource.org/licenses/Apache-2.0")
                    }
                }
                developers {
                    developer {
                        id.set("masahito")
                        name.set("Masato Nakamura")
                        email.set("randomstep@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git@github.com:masahitojp/bqdatamapper4k.git")
                    developerConnection.set("scm:git:ssh://github.com:masahitojp/bqdatamapper4k.git")
                    url.set("https://github.com/masahitojp/bqdatamapper4k")
                }
            }
        }
    }
    repositories {
        maven {
            name = "MavenCentral"
            val releasesRepoUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2"
            val snapshotsRepoUrl = "https://oss.sonatype.org/content/repositories/snapshots"
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            credentials {
                username = sonatypeUsername
                password = sonatypePassword
            }
        }
    }
}

signing {
    sign(publishing.publications["maven"])
}