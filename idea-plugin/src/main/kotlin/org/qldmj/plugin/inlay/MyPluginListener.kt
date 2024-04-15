package org.qldmj.plugin.inlay

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.vfs.VirtualFile

/**
 * 文件打开监听，打开一个文件后开始解析文件
 */
class MyPluginListener: FileEditorManagerListener {

    override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
        val editor = source.selectedTextEditor ?: return
        val project = source.project
        val inlayDemo = project.getService(InlayDemo::class.java)
        inlayDemo.installInlay(editor, file)
    }

}