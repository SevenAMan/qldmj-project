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
    }

    fun setVisible() {
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