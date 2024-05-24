package org.qldmj.plugin.xdebug

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.DialogBuilder
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder

class XmlDebugAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val fileField = JBTextField(30).apply { text = "file://D:/project/compose/sb-server/src/main/java/com/StorageServerApplication.java" }
        val lineField = JBTextField(30).apply { text = "10" }

        val panel = FormBuilder.createFormBuilder()
            .addLabeledComponentFillVertically("File:", fileField)
            .addLabeledComponentFillVertically("Line:", lineField)
            .panel
        DialogBuilder(project).apply {
            centerPanel(panel)
            title("Add Breakpoint")
            setOkOperation {
                project.getService(DebugInfoService::class.java).apply {
                    line = lineField.text.toInt()
                    fileUrl = fileField.text
                }
                dispose()
                dialogWrapper.close(DialogWrapper.OK_EXIT_CODE)
            }
        }.show()

    }
}