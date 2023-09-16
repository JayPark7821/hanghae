import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.3" apply false
    id("io.spring.dependency-management") version "1.1.3" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.4.0" apply false
    id("com.palantir.docker") version "0.35.0" apply false

    kotlin("jvm") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22" apply false
    kotlin("plugin.spring") version "1.8.22" apply false
}

java.sourceCompatibility = JavaVersion.VERSION_17

allprojects {
    group = "kr.jay"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }
    tasks.withType<Test> {
        useJUnitPlatform()
    }
    tasks.named<Jar>("jar") {
        enabled = false
    }
}
