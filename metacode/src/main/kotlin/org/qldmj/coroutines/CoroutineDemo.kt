package org.qldmj.coroutines

import kotlinx.coroutines.*

fun main() {

    CoroutineScope(Dispatchers.IO).launch {
        val res = withTimeoutOrNull(10000) {
            var i = 0
            while (true) {
                println("times $i")
                i += 1
                delay(1000)
                if (i > 50) {
                    break
                }
            }
            i
        }
        println(res)
    }
    runBlocking {
        delay(20000)
    }
}