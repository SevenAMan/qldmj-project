plugins {
    kotlin("jvm")
    id("org.jetbrains.intellij") version "1.16.1"
}

intellij {
    version.set("2024.1")
    type.set("IC")
    plugins.set(listOf("java"))
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks {
    patchPluginXml {
        sinceBuild.set("231")
        untilBuild.set("241.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }

}

kotlin {
    jvmToolchain(17)
}