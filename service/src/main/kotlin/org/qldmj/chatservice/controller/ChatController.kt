/*
package org.qldmj.chatservice.controller

import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatController {

    @GetMapping("/chat/test")
    fun getMessages(response: HttpServletResponse) {
        val msg = "这个是一个ai的chat文本！"
        response.characterEncoding = "UTF-8"
        response.contentType = "text/plain"
        for (c in msg) {
            response.writer.write(charArrayOf(c))
            response.flushBuffer()
            Thread.sleep(300)
        }
    }
}
*/
