package org.qldmj.chatservice.component

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class CommandComponent: CommandLineRunner {
    override fun run(vararg args: String?) {
        println("Line Command runner")
    }
}