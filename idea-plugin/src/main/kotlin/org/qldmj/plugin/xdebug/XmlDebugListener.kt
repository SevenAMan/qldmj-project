package org.qldmj.plugin.xdebug

import com.intellij.debugger.engine.DebugProcess
import com.intellij.debugger.engine.DebugProcessAdapterImpl
import com.intellij.debugger.engine.DebugProcessImpl
import com.intellij.debugger.engine.SuspendContextImpl
import com.intellij.debugger.ui.breakpoints.JavaLineBreakpointType
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import com.intellij.xdebugger.XDebuggerManager
import com.intellij.xdebugger.XDebuggerUtil
import com.sun.jdi.ThreadReference

class XmlDebugListener(private val project: Project) : DebugProcessAdapterImpl() {

    private val dataKey = Key.create<MutableList<DebugGraphInfo>>("debugger.graph.list")

    private val breakpointManager = XDebuggerManager.getInstance(project).breakpointManager

    override fun processAttached(process: DebugProcessImpl?) {
        super.processAttached(process)
        val service = project.getService(DebugInfoService::class.java)
        val fileUrl = service.fileUrl ?: return
        val line = service.line ?: return
        val list = mutableListOf<DebugGraphInfo>()
        runReadAction {
            val properties = breakpointManager.addLineBreakpoint(
                XDebuggerUtil.getInstance().findBreakpointType(JavaLineBreakpointType::class.java),
                fileUrl,
                line,
                XmlBreakpointProperties(),
                true
            )
            breakpointManager.updateBreakpointPresentation(properties, null, null)
            list.add(DebugGraphInfo(fileUrl, line))
        }
        process?.putUserData(dataKey, list)
    }

    override fun paused(suspendContext: SuspendContextImpl?) {
        val process = suspendContext?.debugProcess
        val data = process?.getUserData(dataKey)
        data?.let {
            data.size
        }
    }

    override fun threadStopped(proc: DebugProcess, thread: ThreadReference?) {
        super.threadStopped(proc, thread)
    }

    override fun processDetached(process: DebugProcessImpl?, closedByUser: Boolean) {
        super.processDetached(process, closedByUser)
    }
}