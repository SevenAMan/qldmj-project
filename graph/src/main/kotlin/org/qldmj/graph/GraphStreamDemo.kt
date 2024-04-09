package org.qldmj.graph

import org.graphstream.graph.Graph
import org.graphstream.graph.implementations.MultiGraph
import org.graphstream.graph.implementations.SingleGraph
import org.graphstream.ui.graphicGraph.GraphicGraph
import org.graphstream.ui.spriteManager.SpriteManager
import org.graphstream.ui.swing_viewer.SwingViewer
import org.graphstream.ui.swing_viewer.ViewPanel
import org.graphstream.ui.swing_viewer.util.DefaultMouseManager
import org.graphstream.ui.swing_viewer.util.MouseOverMouseManager
import org.graphstream.ui.view.View
import org.graphstream.ui.view.Viewer
import org.graphstream.ui.view.util.InteractiveElement
import org.graphstream.ui.view.util.MouseManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.awt.event.MouseEvent
import java.util.EnumSet
import javax.swing.JFrame
import javax.swing.UIManager

class GraphStreamDemo

val LOGGER: Logger = LoggerFactory.getLogger(GraphStreamDemo::class.java)

/**
 * 获取到图的viewPanel对象
 */
private fun viewPanel() {
    val graph: Graph = MultiGraph("Demo1")
    graph.apply {
        addNode("a").setLabel("node a").setPosition(1, 1)
        addNode("b").setLabel("node b").setPosition(3, 1)
        addNode("c").setLabel("node c").setPosition(3, 3)
        addEdge("ab", "a", "b", true).setLabel("edge ab")
        addEdge("bc", "b", "c", true).setLabel("edge bc")
    }
    graph.setAttribute("ui.stylesheet", "url('graphStream.css')")
    val display = graph.display(false)
    display.defaultView.setMouseManager(object : DefaultMouseManager() {
        override fun getManagedTypes(): EnumSet<InteractiveElement> {
            return EnumSet.of(InteractiveElement.NODE)
        }

        override fun mouseClicked(event: MouseEvent?) {
            super.mouseClicked(event)
        }

    })
}

private fun dragDropPanel(): ViewPanel {
    val graph = MultiGraph("Drag&Drop")
        .apply {
            addNode("A").setAttribute(GraphConstant.ICON, "icon/start.png")
            addNode("B").setAttribute(GraphConstant.ICON, "icon/end.png")
            addNode("C").setAttribute(GraphConstant.LABEL, "C")
            addEdge("AB", "A", "B")
            addEdge("BC", "B", "C")
        }
    val spriteManager = SpriteManager(graph)
    val spriteA = spriteManager.addSprite("A")
    spriteA.setAttribute(GraphConstant.ICON, "/icon/start.png")

    val viewer = SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD)
    viewer.enableAutoLayout()
    val view = viewer.addDefaultView(false)
    return view as ViewPanel
}

fun main() {
    System.setProperty("org.graphstream.ui", "swing")
    /*    val frame = JFrame("Graph Stream")
        frame.setBounds(200, 50, 900, 500)
        frame.isVisible = true
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.contentPane.add(viewPanel())*/
    viewPanel()
}