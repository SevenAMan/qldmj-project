package org.qldmj.graph

import org.graphstream.graph.Graph
import org.graphstream.graph.implementations.MultiGraph
import org.graphstream.ui.spriteManager.SpriteManager
import org.graphstream.ui.swing_viewer.SwingViewer
import org.graphstream.ui.swing_viewer.ViewPanel
import org.graphstream.ui.swing_viewer.util.MouseOverMouseManager
import org.graphstream.ui.view.Viewer
import org.graphstream.ui.view.util.InteractiveElement
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.EnumSet
import javax.swing.JFrame

class GraphStreamDemo

val LOGGER: Logger = LoggerFactory.getLogger(GraphStreamDemo::class.java)

/**
 * 获取到图的viewPanel对象
 */
private fun viewPanel(): ViewPanel {
    val graph: Graph = MultiGraph("Demo1")
    graph.apply {
        addNode("1").setPosition(1, -3)
        addNode("2").setPosition(3, -3)
        addNode("3").setPosition(2, -5)
        addEdge("12", "1", "2")
        addEdge("23", "2", "3")
        addEdge("31", "3", "1")
    }
    LOGGER.info("Stream Graph")
    val viewer = SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD)
    val sman = SpriteManager(graph)
    val sprite1 = sman.addSprite("SA")
    sprite1.attachToNode("1")
    sprite1.setAttribute(GraphConstant.LABEL, "A Label")
    sprite1.setAttribute(GraphConstant.STYLE, "fill-color: red;")
    sprite1.setAttribute(GraphConstant.ICON, "/icon/start.png")
    val viewPanel = viewer.addDefaultView(false) as ViewPanel
    viewPanel.camera.setViewCenter(2.0, 3.0, 4.0)
    viewPanel.camera.viewPercent = 0.5
    viewPanel.enableMouseOptions()
    viewPanel.setMouseManager(MouseOverMouseManager(EnumSet.of(InteractiveElement.EDGE, InteractiveElement.NODE, InteractiveElement.SPRITE)))
    return viewPanel
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
    val frame = JFrame("Graph Stream")
    frame.setBounds(200, 50, 900, 500)
    frame.isVisible = true
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.contentPane.add(viewPanel())
}