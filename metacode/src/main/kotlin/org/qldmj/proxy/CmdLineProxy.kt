package org.qldmj.proxy

import org.qldmj.entity.GitUser
import kotlin.reflect.full.functions

fun main() {
    GitUser::class.functions.forEach {
        println("Git User inner method : ${it.name}")
    }
    val gitUser = GitUser(23, "zs", "/user")

    val idReceiver0 = gitUser::id
    val idReceiver1 = GitUser::id

    val call0 = idReceiver0.call()
    println(call0)
}

fun interface CmdLine {
    fun run(cmd: String)
}