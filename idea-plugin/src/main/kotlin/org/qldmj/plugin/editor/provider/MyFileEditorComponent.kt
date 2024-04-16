package org.qldmj.plugin.editor.provider

import com.intellij.psi.PsiClass
import com.intellij.psi.PsiJavaFile
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.components.BorderLayoutPanel
import java.awt.FlowLayout
import javax.swing.BoxLayout
import javax.swing.JPanel

/**
 * 简单的psi展示（属性，方法和参数，内部类）
 */
class MyFileEditorComponent(private val javaFile: PsiJavaFile) : BorderLayoutPanel() {

    private val panel = JPanel(FlowLayout(FlowLayout.LEFT, 10, 20))

    init {
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        parse()
        addToCenter(ScrollPaneFactory.createScrollPane(panel))
    }

    private fun parse() {
        javaFile.classes.forEach {
            parseClass(it)
        }
    }

    private fun parseClass(psiClass: PsiClass) {
        panel.add(JBLabel("类： ${psiClass.name}"))
        val fields = psiClass.fields
        val size = fields.size
        if (size > 0) {
            panel.add(JBLabel("-------"))
            panel.add(JBLabel("   属性： $size 个"))
            fields.forEachIndexed { index, field ->
                panel.add(JBLabel("   ${index + 1}： ${field.name}"))
            }
        }
        val methods = psiClass.methods
        val methodCount = methods.size
        if (methodCount > 0) {
            panel.add(JBLabel("-------"))
            panel.add(JBLabel("   方法： $methodCount 个"))
            methods.forEachIndexed { index, method ->
                val param = method.parameterList.parameters
                    .joinToString(", ") { p -> p.type.presentableText }
                panel.add(JBLabel("   ${index + 1}： ${method.name}($param)"))
            }
        }

        val innerClasses = psiClass.innerClasses
        if (innerClasses.isNotEmpty()) {
            panel.add(JBLabel("-------"))
            panel.add(JBLabel("   内部类： ${innerClasses.size} 个"))
            innerClasses.forEachIndexed { index, innerClass ->
                panel.add(JBLabel("   ${index + 1}： ${innerClass.name}"))
                parseClass(innerClass)
            }
        }
    }

}