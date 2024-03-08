plugins {
    kotlin("jvm")
}

dependencies {
    implementation(fileTree("/lib"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}