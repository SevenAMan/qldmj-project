package org.qldmj.route

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.freemarker.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.title
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.qldmj.data.*
import org.qldmj.exception.FileNotfoundException
import java.io.File
import java.util.*

fun Application.configRouting() {
    routing {

        get("/favicon.ico") {
            val bytes = this::class.java.classLoader.getResource("icon.png")?.readBytes()
            call.respondBytes(bytes!!, ContentType.Image.PNG)
        }

        authenticate("myKtorAuth") {
            get("/") {
                call.respondHtml {
                    head {
                        title {
                            +"Index"
                        }
                    }
                    body {
                        h1 {
                            +"Hello Ktor !"
                        }
                    }
                }
            }
        }
        route("/users") {
            val allUsers = multiUser()

            get { //序列化json
                call.respondJson(allUsers)
            }

            get("/id/{id}") {
                call.parameters["id"]?.let { stringId ->
                    stringId.toIntOrNull()?.let { id ->
                        call.respondText(allUsers[id].name)
                    }
                }
            }
            get("/{name}") {
                val name = call.parameters["name"] ?: "Default"
                call.respondTemplate("user.ftl", mapOf("userName" to name))
            }

            get("/initTable") {
                transaction {
                    addLogger(StdOutSqlLogger)
                    SchemaUtils.create(User)
                }
            }
        }

        route("/rest") {
            get {
                call.respondRedirect("/")
            }

            post("/login") {
                call.sessions.set(UserSession(UUID.randomUUID(), AccessLevel.USER))
//                call.respondRedirect("/rest")
            }

            post {
                val parameters = call.receiveParameters()
                val name = parameters["name"]
                val id = parameters["id"]
                val builder = StringBuilder()
                builder.append("name: $name; id: $id \n")
                call.request.headers.forEach { key, value ->
                    builder.append("$key : $value; \n")
                }
                val cookies = call.request.cookies
                builder.append("cookies ---- \n")
                cookies.rawCookies.forEach { (key, value) ->
                    builder.append("$key : $value; \n")
                }
                call.respondText { builder.toString() }
            }
            post("/file") {
                val multipart = call.receiveMultipart()
                var responseString = ""
                for (part in multipart.readAllParts()) {
                    if (part is PartData.FormItem) {
                        responseString += "${part.name} : ${part.value}; "
                    }
                    if (part is PartData.FileItem) {
                        responseString += "fileName: ${part.originalFileName}; "
                    }
                }
                call.respondText(responseString)
            }
            get("/file/download") {
                val file = this::class.java.classLoader.getResource("logback.xml2")?.file
                if (file == null) {
                    throw FileNotfoundException()
                } else
                    call.response.header("Content-Disposition", "attachment")
                call.respondFile(File(file))
            }
        }
    }
}

suspend inline fun <reified T : Any> ApplicationCall.respondJson(t: T) {
    respond(Json.encodeToString(t))
}