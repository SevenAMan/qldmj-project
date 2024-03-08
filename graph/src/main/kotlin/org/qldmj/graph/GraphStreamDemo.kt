package org.qldmj.graph


import org.graphstream.graph.implementations.SingleGraph
import org.slf4j.LoggerFactory

class GraphStreamDemo

fun main() {
    System.setProperty("org.graphstream.ui", "swing")
    val graph = SingleGraph("Demo1")
    graph.apply {
        addNode("1")
        addNode("2")
        addNode("3")
        addEdge("12", "1", "2")
        addEdge("23", "2", "3")
        addEdge("31", "3", "1")
    }
    LoggerFactory.getLogger(GraphStreamDemo::class.java).info("Stream Graph")
    val display = graph.display()
}