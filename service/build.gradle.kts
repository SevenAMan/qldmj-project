plugins {
    kotlin("jvm") version KotlinVersion.VERSION
    kotlin("plugin.spring") version KotlinVersion.VERSION
    id("io.spring.dependency-management") version SpringVersion.DEPENDENCY_MANAGEMENT_VERSION
    id("org.springframework.boot") version SpringVersion.BOOT_VERSION
    `java-library`
}

group = "org.qldmj"
version = "0.0.1-SNAPSHOT"

dependencies {
    api(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}