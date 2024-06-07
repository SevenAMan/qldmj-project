plugins {
    id("java")
    kotlin("jvm")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.22")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.17.1")
    implementation("org.codehaus.janino:janino:3.1.12")

    implementation("org.slf4j:slf4j-api:2.0.12")
    implementation("ch.qos.logback:logback-core:1.4.14")
    implementation("ch.qos.logback:logback-classic:1.4.14")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}