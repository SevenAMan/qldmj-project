plugins {
    id("io.ktor.plugin") version "2.3.10"
    kotlin("plugin.serialization") version "1.9.22"
    kotlin("jvm")
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-tomcat-jvm")
    //freemarker
    implementation("io.ktor:ktor-server-freemarker")
    //status pages
    implementation("io.ktor:ktor-server-status-pages")
    //sessions
    implementation("io.ktor:ktor-server-sessions")
    //log
    implementation("ch.qos.logback:logback-classic:1.4.14")

    //命令行参数
    implementation("org.jetbrains.kotlinx:kotlinx-cli-jvm:0.3.6")

    //dispose
    implementation("org.jetbrains.exposed:exposed-core:0.49.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.49.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.49.0")
    implementation("mysql:mysql-connector-java:8.0.28")

    //HTML DSL
    implementation("io.ktor:ktor-server-html-builder-jvm")

    //auth
    implementation("io.ktor:ktor-server-auth")

    //jwt
    implementation("io.ktor:ktor-server-auth-jwt")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}