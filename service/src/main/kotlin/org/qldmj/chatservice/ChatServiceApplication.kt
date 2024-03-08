package org.qldmj.chatservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChatServiceApplication

fun main(vararg args: String) {
    runApplication<ChatServiceApplication>(*args)
}
