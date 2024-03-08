
apply<EnterpriseRepositoryPlugin>()

class EnterpriseRepositoryPlugin: Plugin<Gradle> {
    companion object {
        const val MAVEN_CENTRAL = "https://repo1/maven.org.maven"
        const val GRADLE_PLUGIN = "https://plugins.gradle.org/m2/"
        const val MAVEN_REPO = "https://repo.maven.apache.org/maven2"

        const val ALI_CENTRAL = "https://maven.aliyun.com/repository/central/"
        const val ALI_PUBLIC = "https://maven.aliyun.com/repository/public/"
        const val ALI_GRADLE_PLUGIN = "https://maven.aliyun.com/repository/gradle-plugin/"

        const val TENCENT_MAVEN = "https://mirrors.tencent.com/nexus/repository/maven-public/"
        const val TENCENT_GRADLE_PLUGIN = "https://mirrors.tencent.com/nexus/repository/gradle-plugins/"
    }

    override fun apply(gradle: Gradle) {
        gradle.settingsEvaluated {
            pluginManagement {
                repositories {
                    all {
                        if (this is MavenArtifactRepository) {
                            val url = this.url.toString()
                            if (url.startsWith(MAVEN_CENTRAL)) {
                                this.setUrl(ALI_CENTRAL)
                            }
                            if ( url.startsWith(MAVEN_REPO)) {
                                this.setUrl(ALI_PUBLIC)
                            }
                            if (url.startsWith(GRADLE_PLUGIN)) {
                                this.setUrl(ALI_GRADLE_PLUGIN)
                            }
                        }
                    }
                }
            }

        }

        gradle.allprojects {
            repositories {
                all {
                    if (this is MavenArtifactRepository) {
                        val url = this.url.toString()
                        if (url.startsWith(MAVEN_CENTRAL)) {
                            this.setUrl(ALI_CENTRAL)
                        }
                        if ( url.startsWith(MAVEN_REPO)) {
                            this.setUrl(ALI_PUBLIC)
                        }
                        if (url.startsWith(GRADLE_PLUGIN)) {
                            this.setUrl(ALI_GRADLE_PLUGIN)
                        }
                    }
                }
            }
        }
    }
}