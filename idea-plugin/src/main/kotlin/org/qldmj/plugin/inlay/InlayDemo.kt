package org.qldmj.plugin.inlay

import com.intellij.icons.AllIcons
import com.intellij.openapi.components.Service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiJavaFile
import com.intellij.psi.PsiManager
import com.intellij.psi.PsiModifier
import com.intellij.refactoring.suggested.startOffset

@Service(Service.Level.PROJECT)
class InlayDemo(private val project: Project) {

    companion object {
        const val REQUEST_MAPPING = "org.springframework.web.bind.annotation.RequestMapping"
    }

    /**
     * 解析VirtualFile结构，解析注解 RequestMapping 中的 value属性，即 URL
     */
    fun installInlay(editor: Editor, vfs: VirtualFile) {
        val file = PsiManager.getInstance(project).findFile(vfs)
        if (file is PsiJavaFile) {
            val clazz = file.classes.find { it.name == vfs.nameWithoutExtension } ?: return
            val values =
                clazz.methods.filter { it.hasModifierProperty(PsiModifier.PUBLIC) && it.hasAnnotation(REQUEST_MAPPING) }
                    .map {
                        it.getAnnotation(REQUEST_MAPPING)!!
                    }
                    .map { annotation -> annotation.findAttributeValue("value") }.toList()
            values.filterNotNull().forEach {
                val offset = it.startOffset
                editor.inlayModel.addInlineElement(offset, AnnotationAttributeValueEditorCustomElementRenderer(editor, AllIcons.Xml.Browsers.Chrome))
            }
        }
    }
}