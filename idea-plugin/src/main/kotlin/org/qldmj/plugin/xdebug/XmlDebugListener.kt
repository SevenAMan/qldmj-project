package org.qldmj.plugin.xdebug

import com.intellij.debugger.engine.DebugProcessAdapterImpl
import com.intellij.debugger.engine.DebugProcessImpl
import com.intellij.debugger.engine.SuspendContextImpl
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.project.Project
import com.intellij.xdebugger.XDebuggerManager
import com.intellij.xdebugger.XDebuggerUtil

class XmlDebugListener(private val project: Project) : DebugProcessAdapterImpl() {
    private val breakpointManager = XDebuggerManager.getInstance(project).breakpointManager

    override fun processAttached(process: DebugProcessImpl?) {
        val service = project.getService(DebugInfoService::class.java)
        val fileUrl = service.fileUrl ?: return
        val line = service.line ?: return
        runReadAction {
            val properties = breakpointManager.addLineBreakpoint(
                XDebuggerUtil.getInstance().findBreakpointType(XmlLineBreakpointType::class.java),
                fileUrl,
                line,
                XmlBreakpointProperties(),
                true
            )
            properties.type is XmlLineBreakpointType
        }
    }

    override fun paused(suspendContext: SuspendContextImpl?) {

    }

}