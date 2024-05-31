package org.qldmj.chatservice

import kotlinx.coroutines.*
import org.springframework.boot.builder.SpringApplicationBuilder
import java.awt.EventQueue
import java.awt.FlowLayout
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.WindowConstants

class SwingApplication : JFrame() {

    private val aLabel = JLabel("Label")
    private val textField = JTextField(30)

    init {
        title = "Swing Application"
        contentPane = JPanel(FlowLayout(FlowLayout.LEFT, 10, 20)).apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            add(aLabel)
            add(JLabel("Label1"))
            add(JLabel("Label2"))
            add(textField)
            add(JButton("Chang label").apply {
                addActionListener {
                    CoroutineScope(Dispatchers.Default).launch {
                        val newText = textField.text
                        delay(4000)
                        withContext(Dispatchers.Main) {
                            aLabel.text = if (newText == "") "New label" else newText
                        }
                    }
                }
            })

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