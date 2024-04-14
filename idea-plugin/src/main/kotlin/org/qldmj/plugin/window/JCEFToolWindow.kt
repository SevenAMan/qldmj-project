package org.qldmj.plugin.window

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import org.qldmj.plugin.jcef.MyJCEFClient

class JCEFToolWindow : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myJCEFClient = project.getService(MyJCEFClient::class.java)
        val content = ContentFactory.getInstance().createContent(myJCEFClient.component, "JCEFToolWindow", false)
        toolWindow.contentManager.addContent(content)
    }
}