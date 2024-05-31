package org.qldmj.graphx

import com.mxgraph.swing.mxGraphComponent
import com.mxgraph.view.mxGraph
import javax.swing.JFrame
import javax.swing.WindowConstants

fun main() {
    val graph = mxGraph()
    val n1 = graph.insertVertex("1", "1", 20, 20.0, 20.0, 20.0, 20.0)
    val n2 = graph.insertVertex("2", "3", 20, 40.0, 20.0, 20.0, 20.0)
    graph.insertEdge(null, "e1", n1, n2, null)
    val component = mxGraphComponent(graph)
    JFrame("Hello World").apply {
        contentPane.add(component)
        isVisible = true
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    }
}