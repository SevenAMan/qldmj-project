package org.qldmj.plugin.xdebug

import com.intellij.debugger.ui.breakpoints.JavaLineBreakpointType
import com.intellij.openapi.vfs.VirtualFile
import org.jetbrains.java.debugger.breakpoints.properties.JavaLineBreakpointProperties

class XmlLineBreakpointType : JavaLineBreakpointType() {

    var flowFile: VirtualFile? = null

    override fun createProperties(): JavaLineBreakpointProperties {
        return XmlBreakpointProperties()
    }
}

fun getInstance(file: VirtualFile): XmlLineBreakpointType {
    return XmlLineBreakpointType().apply {
        flowFile = file
    }
}