package org.qldmj.graphx.config

import com.mxgraph.swing.mxGraphComponent.*
import org.qldmj.graphx.component.BasicGraphComponent
import java.awt.Color

/**
 * 配置画布的页面
 */
fun BasicGraphComponent.configPage() {

}

/**
 * 缩放配置
 */
fun BasicGraphComponent.configZoom() {
    zoomFactor = 1.2                        //缩放比例
    zoomPolicy = ZOOM_POLICY_WIDTH          //缩放策略 default：宽度
    isKeepSelectionVisibleOnZoom = true     //缩放时选择的节点可见
    isCenterZoom = true                     //中心缩放
    graph.view.scale = 1.0                  //默认的缩放比例
}

/**
 * 配置网格线
 */
fun BasicGraphComponent.configGrid() {
    isGridVisible = true
    gridStyle = GRID_STYLE_DASHED
    gridColor = Color(0xE7E7E7)
    graph.gridSize = 20
    graph.isGridEnabled = false //节点贴靠

}