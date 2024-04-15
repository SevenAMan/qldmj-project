package org.qldmj.plugin.inlay

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorCustomElementRenderer
import com.intellij.openapi.editor.Inlay
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.util.IconUtil
import java.awt.Graphics
import java.awt.Rectangle
import javax.swing.Icon
import javax.swing.UIManager

/**
 * inlay展示样式， 展示一个图标
 */
class AnnotationAttributeValueEditorCustomElementRenderer(private val editor: Editor, private val icon: Icon)
    : EditorCustomElementRenderer {
    override fun calcWidthInPixels(inlay: Inlay<*>): Int {
        return editor.contentComponent.getFontMetrics(UIManager.getFont("Label.font")).height
    }

    override fun paint(inlay: Inlay<*>, g: Graphics, targetRegion: Rectangle, textAttributes: TextAttributes) {
        val image = IconUtil.toImage(icon)
        g.drawImage(image, targetRegion.x, targetRegion.y, null)
    }
}