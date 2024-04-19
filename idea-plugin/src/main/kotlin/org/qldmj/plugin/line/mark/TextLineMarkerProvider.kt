package org.qldmj.plugin.line.mark

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.vfs.VfsUtilCore
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.PsiPlainText

class TextLineMarkerProvider : LineMarkerProvider {

    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<*>? {
        return null
    }

    override fun collectSlowLineMarkers(elements: MutableList<out PsiElement>, result: MutableCollection<in LineMarkerInfo<*>>) {
        elements.forEach { element ->
            if (element.containingFile.virtualFile.extension == "txt" && element is PsiPlainText) {
                val text = element.text

                val textRange: TextRange = element.textRange
                val filePairs = mutableListOf<Pair<VirtualFile, TextRange>>()
                text.split("\n").filter { it != "" }.forEach {
                    val file = VfsUtilCore.findRelativeFile(it, element.containingFile.virtualFile)
                    if (file != null) {
                        val start = textRange.startOffset + text.indexOf(it)
                        filePairs.add(Pair(file, TextRange(start, start + it.length)))
                    }
                }

                filePairs.forEach { (file, range) ->
                    val marker = LineMarkerInfo<PsiElement>(
                        element,
                        range,
                        file.fileType.icon,
                        { "Open file: ${file.path}" },
                        { _, _ ->
                            PsiManager.getInstance(element.project).findFile(file)!!.navigate(true)
                        },
                        GutterIconRenderer.Alignment.CENTER
                    ) { "Open file: ${file.path}" }
                    result.add(marker)
                }

            }
        }
    }
}