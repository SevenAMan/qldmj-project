plugins {
    kotlin("jvm")
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.12")
    implementation("ch.qos.logback:logback-core:1.4.14")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    testImplementation("ch.qos.logback:logback-classic:1.4.14")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
