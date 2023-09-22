import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.3"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.jlleitschuh.gradle.ktlint") version "11.4.0"
    id("com.palantir.docker") version "0.35.0"

    kotlin("plugin.jpa") version "1.8.22"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}

allprojects{
    java {
        sourceCompatibility = JavaVersion.VERSION_17
    }


    group = "kr.jay"
    version = "1.0.0"
}
subprojects{
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
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "com.palantir.docker")

    dependencies {

        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        implementation("com.h2database:h2:1.4.200")
        runtimeOnly("com.mysql:mysql-connector-j")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")

        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.rest-assured:rest-assured")
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs += "-Xjsr305=strict"
                jvmTarget = "17"
            }
        }
        withType<Test> {
            useJUnitPlatform()
        }
        named<Jar>("jar") {
            enabled = false
        }
    }

}

