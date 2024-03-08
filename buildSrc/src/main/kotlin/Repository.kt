import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.maven

fun RepositoryHandler.alibabaPublic() = maven("https://maven.aliyun.com/repository/public/")
fun RepositoryHandler.alibabaCentral() = maven("https://maven.aliyun.com/repository/central/")
fun RepositoryHandler.alibabaGradle() = maven("https://maven.aliyun.com/repository/gradle-plugin/")

fun RepositoryHandler.tencentPublic() = maven("https://mirrors.tencent.com/nexus/repository/maven-public/")
fun RepositoryHandler.tencentGradle() = maven("https://mirrors.tencent.com/nexus/repository/gradle-plugins/")

