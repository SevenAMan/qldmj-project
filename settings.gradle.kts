plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "qldmj-project"

include("service")
include("common")
include("metacode")
include("graph")
