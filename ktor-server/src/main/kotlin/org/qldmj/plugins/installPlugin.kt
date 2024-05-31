package org.qldmj.plugins

import freemarker.cache.ClassTemplateLoader
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.freemarker.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import org.qldmj.data.UserSession
import org.qldmj.exception.FileNotfoundException

fun Application.configPlugin() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    //状态处理
    install(StatusPages) {

        //异常处理
        exception<FileNotfoundException> { call, cause ->
            call.respondText(text = "500: ${cause.message}", status = HttpStatusCode.InternalServerError)
        }
        //页面跳转
        statusFile(
            HttpStatusCode.NotFound,
            HttpStatusCode.BadRequest,
            HttpStatusCode.InternalServerError,
            filePattern = "pages/error/#.html"
        )
    }

    //sessions
    install(Sessions) {
        cookie<UserSession>("user_session") {
            cookie.path = "/"
            cookie.maxAgeInSeconds = 10
        }
    }

    //auth
    install(Authentication) {
        basic("myKtorAuth") {
            realm = "myKtorAuth"
            validate { credentials ->
                if (credentials.name == "zs" && credentials.password == "zs") {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }
    }
}