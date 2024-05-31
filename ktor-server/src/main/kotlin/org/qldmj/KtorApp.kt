package org.qldmj

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.request.*
import io.ktor.server.tomcat.*
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.required
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.qldmj.data.connectionDatabase
import org.qldmj.plugins.configPlugin
import org.qldmj.route.configRouting
import org.slf4j.LoggerFactory

fun main(args: Array<String>) {
    val parser = ArgParser("DatabaseConfiguration")
    val host by parser.option(ArgType.String, shortName = "host").required()
    val port by parser.option(ArgType.String, shortName = "port").required()
    val database by parser.option(ArgType.String, shortName = "database").required()
    val user by parser.option(ArgType.String, shortName = "user").required()
    val password by parser.option(ArgType.String, shortName = "password").required()
    parser.parse(args)

    CoroutineScope(Dispatchers.IO).launch {
        val logger = LoggerFactory.getLogger("Expose:")
        logger.info("Expose start connection ....")
        val connection = connectionDatabase(host, port, database, user, password)
        logger.info("MySQL Version: ${connection.version}")
    }

    embeddedServer(
        Tomcat,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    //拦截器
    intercept(ApplicationCallPipeline.Call) {
        LoggerFactory.getLogger("Ktor").info(call.request.path())
    }
    configPlugin()

    configRouting()
}