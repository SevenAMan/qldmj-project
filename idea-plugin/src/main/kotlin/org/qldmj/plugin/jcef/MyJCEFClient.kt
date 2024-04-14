package org.qldmj.plugin.jcef

import com.intellij.openapi.components.Service
import com.intellij.ui.jcef.JBCefBrowser

@Service(Service.Level.PROJECT)
class MyJCEFClient : JBCefBrowser() {

    fun load(text: String) {
        loadURL("https://www.baidu.com/s?wd=$text")
    }


}