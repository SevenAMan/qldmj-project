package org.qldmj.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import org.slf4j.LoggerFactory
import kotlin.time.measureTime

class CoroutineDemo

private val logger = LoggerFactory.getLogger(CoroutineDemo::class.java)

/*fun main() = runBlocking {
    val job = launch(Dispatchers.Default) {
        repeat(5) { index ->
            try {
                logger.info("loop job $index")
                delay(500)
            } catch (e: CancellationException) {
                logger.error("loop job $index cancelled", e)
            }
        }
    }
    delay(1300)
    logger.info("Waiting ...")
    job.cancelAndJoin()
    logger.info("Done!")
}*/

/*
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var index = 0
        var nextPrintTime = startTime
        while (index < 5 && isActive) {
            if (System.currentTimeMillis() >= nextPrintTime) {
                logger.info("loop :${index++}")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L)
    logger.info("waiting !")
    job.cancelAndJoin()
    logger.info("down!")
}*/

/*fun main() = runBlocking {

    val job = launch(Dispatchers.Default) {
        try {
            repeat(100) { index ->
                logger.info("Repeat $index")
                delay(1000L)
            }
        } catch (e: Exception) {
            logger.error("Coroutines cancel exception", e)
        } finally {
            withContext(NonCancellable) {
                logger.info("Finally start")
                delay(1000L)
                logger.info("Finally end")
            }
        }
    }
    delay(4000L)
    logger.info("Waiting for job to finish!")
    job.cancelAndJoin()
    logger.info("Done!")
}*/

/*
fun main() = runBlocking {
    val loopIndex = withTimeoutOrNull(4000L) {
        var index = 0
        repeat(20) {
            logger.info("Repeat $it")
            index = 10
            delay(300L)
        }
        index
    }
    logger.info("$loopIndex")
}
*/

/*
fun main() = runBlocking {
    logger.info("Start!")
    ThreadLocal<String?>().set("Coroutine1")
    val v1 = async(Dispatchers.IO) {
        delay(1000)
        logger.info("6")
        6
    }
    val v2 = async {
        delay(1000)
        logger.info("7")
        7
    }
    val time = measureTime {
        logger.info("${v1.await() * v2.await()}")
    }
    logger.info("$time")
}*/

fun main() = runBlocking {
    val sequence = flow {
        logger.info("Start")
        for (index in 1..3) {
            delay(1000L)
            emit(index)
        }
    }
    launch {
        withContext(Dispatchers.Default) {
            sequence.collect {
                logger.info("$it")
            }
        }
    }
    logger.info("after flow")
}
