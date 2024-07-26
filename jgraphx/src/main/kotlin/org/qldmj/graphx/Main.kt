package org.qldmj.graphx

import org.qldmj.graphx.component.BasicGraph
import org.qldmj.graphx.component.BasicGraphComponent
import org.qldmj.graphx.component.addEdge
import org.qldmj.graphx.component.addNode
import org.qldmj.graphx.data.BasicEdgeValue
import org.qldmj.graphx.data.BasicNodeValue
import javax.swing.JFrame
import javax.swing.WindowConstants

fun main() {
    val graph = BasicGraph()
    val n1 = graph.addNode(BasicNodeValue().apply {
        id = "N0001"
        comment = id
        width = 30.0
        height = 30.0
        x = 20.0
        y = 20.0
    })
    val n2 = graph.addNode(BasicNodeValue().apply {
        id = "N0002"
        comment = id
        width = 30.0
        height = 30.0
        x = 80.0
        y = 80.0
    })
    graph.addEdge(BasicEdgeValue().apply {
        id = "S0001"
        comment = id
        fromId = n1.id
        toId = n2.id
    }, n1, n2)
    val component = BasicGraphComponent(graph)
    JFrame("Hello World").apply {
        contentPane.add(component)
        isVisible = true
        setBounds(200, 200, 600, 400)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    }
}