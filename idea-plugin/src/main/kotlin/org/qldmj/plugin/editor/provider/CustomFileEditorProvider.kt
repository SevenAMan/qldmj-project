package org.qldmj.plugin.editor.provider

import com.intellij.ide.highlighter.JavaFileType
import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorPolicy
import com.intellij.openapi.fileEditor.FileEditorProvider
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

/**
 * 给java文件添加解析的额外视图
 */
class CustomFileEditorProvider : FileEditorProvider {

    companion object {
        const val EDITOR_ID = "myCustomEditorId"
    }

    override fun accept(project: Project, file: VirtualFile): Boolean {
        return file.fileType == JavaFileType.INSTANCE
    }

    override fun createEditor(project: Project, file: VirtualFile): FileEditor {
        return MyCustomFileEditor(project, file)
    }

    override fun getEditorTypeId(): String = EDITOR_ID

    override fun getPolicy(): FileEditorPolicy {
        return FileEditorPolicy.PLACE_AFTER_DEFAULT_EDITOR
    }
}