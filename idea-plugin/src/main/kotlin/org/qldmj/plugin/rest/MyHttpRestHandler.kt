package org.qldmj.plugin.rest

import com.fasterxml.jackson.core.JsonFactory
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.DialogBuilder
import com.intellij.openapi.util.io.BufferExposingByteArrayOutputStream
import com.intellij.ui.components.JBLabel
import com.intellij.util.io.jackson.obj
import com.intellij.util.ui.components.BorderLayoutPanel
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.http.FullHttpRequest
import io.netty.handler.codec.http.QueryStringDecoder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.ide.RestService

class MyHttpRestHandler : RestService() {
    override fun execute(
        urlDecoder: QueryStringDecoder,
        request: FullHttpRequest,
        context: ChannelHandlerContext
    ): String? {
        val projects = ProjectManager.getInstance().openProjects
        val out = BufferExposingByteArrayOutputStream()
        JsonFactory().createGenerator(out).useDefaultPrettyPrinter().use { writer ->
            writer.obj {
                projects.forEach { project ->
                    writer.writeStringField(project.name, project.basePath)
                }
            }
        }
        send(out, request, context)
        CoroutineScope(Dispatchers.IO).launch {
            ApplicationManager.getApplication().invokeLater {
                DialogBuilder(projects[0]).apply {
                    title("Messages").centerPanel(BorderLayoutPanel().addToCenter(JBLabel("有人在浏览器看我了！")))
                }.show()
            }
        }
        return null
    }

    override fun getServiceName(): String {
        return "my-http"
    }
}