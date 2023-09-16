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

    dependencies {
//        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
//        testImplementation("org.springframework.boot:spring-boot-starter-test")
//        testImplementation("io.rest-assured:rest-assured")
//        implementation("com.h2database:h2:1.4.200")
//        runtimeOnly("com.mysql:mysql-connector-j")
//        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//        implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
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






//docker {
//    name = project.name + ":" + version
//    setDockerfile(file("Dockerfile"))
//    files(tasks.bootJar.get().outputs.files)
//    buildArgs(
//        mapOf(
//            "JAR_FILE" to tasks.bootJar.get().outputs.files.singleFile.name
//        )
//    )
//}
