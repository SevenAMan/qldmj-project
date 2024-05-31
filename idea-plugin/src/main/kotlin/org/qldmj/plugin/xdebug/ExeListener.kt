package org.qldmj.plugin.xdebug

import com.intellij.debugger.DebuggerManager
import com.intellij.execution.ExecutionListener
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.runners.ExecutionEnvironment

class ExeListener: ExecutionListener {

    override fun processStarted(executorId: String, env: ExecutionEnvironment, handler: ProcessHandler) {
        super.processStarted(executorId, env, handler)
        DebuggerManager.getInstance(env.project)
            .addDebugProcessListener(handler, XmlDebugListener(project = env.project))
    }
}