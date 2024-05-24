package org.qldmj.plugin.xdebug

import com.intellij.openapi.components.Service

@Service(Service.Level.PROJECT)
data class DebugInfoService(var line: Int? = null, var fileUrl: String? = null)