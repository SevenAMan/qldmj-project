package org.qldmj.graph

import org.graphstream.graph.Edge
import org.graphstream.graph.Node

object GraphConstant {

    const val XYZ = "xyz"

    //图标
    const val ICON = "ui.icon"
    const val LABEL = "ui.label"
    const val STYLE = "ui.style"
    const val DATA = "node.data"
}

fun <T : Number> Node.setPosition(x: T, y: T): Node {
    this.setAttribute(GraphConstant.XYZ, x, y)
    return this
}

fun Node.setLabel(label: String): Node {
    this.setAttribute(GraphConstant.LABEL, label)
    return this
}

fun <T> Node.setData(data: T): Node {
    this.setAttribute(GraphConstant.DATA, data)
    return this
}

fun Node.getData(): Any? {
    return if (this.hasAttribute(GraphConstant.DATA))
        this.getAttribute(GraphConstant.DATA)
    else null
}

fun Edge.setLabel(label: String): Edge {
    this.setAttribute(GraphConstant.LABEL, label)
    return this
}