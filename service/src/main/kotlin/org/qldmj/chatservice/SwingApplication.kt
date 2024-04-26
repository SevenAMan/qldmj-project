package org.qldmj.chatservice

import org.springframework.boot.builder.SpringApplicationBuilder
import java.awt.BorderLayout
import java.awt.EventQueue
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.WindowConstants

class SwingApplication : JFrame() {

    init {
        title = "Swing Application"
        contentPane = JPanel(BorderLayout()).apply {
            add(JLabel("Label: "), BorderLayout.CENTER)
        }
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        setLocationRelativeTo(null) //相对位置
    }

    fun setVisible() {
        pack() //内部组件的布局对齐方式
        isVisible = true
    }
}

fun main(vararg args: String) {
    val context = SpringApplicationBuilder(SwingApplication::class.java).headless(false).run(*args)

    EventQueue.invokeLater {
        val frame = context.getBean(SwingApplication::class.java)
        frame.setVisible()
    }
}