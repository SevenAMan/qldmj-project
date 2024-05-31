package org.qldmj.graphx.component

import com.mxgraph.model.mxCell
import org.qldmj.graphx.data.BasicEdgeValue
import org.qldmj.graphx.data.BasicNodeValue

fun BasicGraph.addNode(nodeValue: BasicNodeValue): mxCell {
    val node = this.insertVertex(
        null, nodeValue.id, nodeValue,
        nodeValue.x, nodeValue.y, nodeValue.width, nodeValue.height
    )
    return node as mxCell
}

fun BasicGraph.addEdge(edgeValue: BasicEdgeValue, startNode: mxCell? = null, endNode: mxCell? = null): mxCell {
    val edge = this.insertEdge(null, edgeValue.id, edgeValue, startNode, endNode)
    return edge as mxCell
}