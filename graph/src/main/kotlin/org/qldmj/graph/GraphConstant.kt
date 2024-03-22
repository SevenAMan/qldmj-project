package org.qldmj.graph

import org.graphstream.graph.Node

object GraphConstant {

    const val XYZ = "xyz"

    //图标
    const val ICON = "ui.icon"
    const val LABEL = "ui.label"
    const val STYLE = "ui.style"
}

fun <T : Number> Node.setPosition(x: T, y: T): Node {
    this.setAttribute(GraphConstant.XYZ, x, y)
    return this
}