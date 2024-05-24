package org.qldmj.plugin.action

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.wm.ToolWindowManager
import org.qldmj.plugin.jcef.MyJCEFClient

class JCEFAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val text = getSelectText(e)
        val project = e.project ?: return
        val myJCEFClient = project.getService(MyJCEFClient::class.java)
        myJCEFClient.load(text)

        ApplicationManager.getApplication().invokeLater {
            ToolWindowManager.getInstance(project).getToolWindow("JCEFToolWindow")?.show()
        }

        object: Task.Backgroundable(project, "Loading JCEF...") {
            override fun run(indicator: ProgressIndicator) {
                TODO("Not yet implemented")
            }

        }.queue()
    }

    override fun update(e: AnActionEvent) {
        e.presentation.text = "百度搜索"
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    private fun getSelectText(e: AnActionEvent): String {
        return e.getData(LangDataKeys.PSI_ELEMENT)?.text ?: return ""
    }
}