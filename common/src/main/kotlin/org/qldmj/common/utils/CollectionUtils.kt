package org.qldmj.common.utils

object CollectionUtils {
    fun List<Any>.getString(): String {
        return this.joinToString(", ")
    }
}

fun main() {
    val regex = "<[^>]+>"
    val exp = "java.lang.Map<java.lang.String,java.lang.Object>"
    val result = regex.toRegex().find(exp)
    println(result)
}