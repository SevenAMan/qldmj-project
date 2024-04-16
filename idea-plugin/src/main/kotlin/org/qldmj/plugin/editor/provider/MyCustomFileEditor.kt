package org.qldmj.plugin.editor.provider

import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorState
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiJavaFile
import com.intellij.psi.PsiManager
import java.beans.PropertyChangeListener
import javax.swing.JComponent

/**
 * 文件的editor视图
 */
class MyCustomFileEditor(project: Project, private val virtualFile: VirtualFile) :
    FileEditor {

    private var editorComponent: MyFileEditorComponent?
    private val javaFile: PsiJavaFile = PsiManager.getInstance(project).findFile(virtualFile) as PsiJavaFile

    init {
        editorComponent = MyFileEditorComponent(javaFile)
    }

    override fun <T : Any?> getUserData(key: Key<T>): T? {
        return null
    }

    override fun <T : Any?> putUserData(key: Key<T>, value: T?) {

    }

    override fun dispose() {
        editorComponent = null
    }

    override fun getComponent(): JComponent {
        return editorComponent ?: MyFileEditorComponent(javaFile)
    }

    override fun getPreferredFocusedComponent(): JComponent? {
        return editorComponent
    }

    override fun getName(): String {
        return file.nameWithoutExtension
    }

    override fun setState(state: FileEditorState) {
    }

    override fun isModified(): Boolean = false

    override fun isValid(): Boolean = true

    override fun addPropertyChangeListener(listener: PropertyChangeListener) {

    }

    override fun removePropertyChangeListener(listener: PropertyChangeListener) {

    }

    override fun getFile(): VirtualFile {
        return virtualFile
    }
}