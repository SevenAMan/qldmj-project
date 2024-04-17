package org.qldmj.plugin.database

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogBuilder
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.components.JBPasswordField
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.awt.FlowLayout
import java.io.File
import java.net.URL
import java.net.URLClassLoader
import java.sql.Connection
import java.sql.Driver
import java.sql.DriverManager
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel

class DatabaseLoadDriverDialog(project: Project) : DialogBuilder(project) {

    companion object {
        const val DS_DRIVER = "ds.driver.key"
        const val DS_NAME = "ds.name.key"
        const val DS_COMM = "ds.comm.key"
        const val DS_HOST = "ds.host.key"
        const val DS_PORT = "ds.port.key"
        const val DS_USER = "ds.user.key"
        const val DS_PASS = "ds.pass.key"
    }

    private val propertiesComponent = PropertiesComponent.getInstance(project)

    private val driverField = TextFieldWithBrowseButton()

    private val typeBox = ComboBox<DatabaseType>()

    private val nameField = JBTextField(30)
    private val commField = JBTextField(30)

    private val hostField = JBTextField(30)
    private val portField = JBTextField(30)

    private val userField = JBTextField(30)
    private val passField = JBPasswordField()

    private val loadButton = JButton("加载驱动")
    private val unloadButton = JButton("卸载驱动")
    private val connectionButton = JButton("连接测试")


    init {
        DatabaseType.entries.forEach {
            typeBox.addItem(it)
        }
        typeBox.selectedItem = DatabaseType.MYSQL

        driverField.addBrowseFolderListener("驱动选择", "", project,
            FileChooserDescriptor(false, false, true, true, false, false)
                .withFileFilter { "jar" == it.getExtension() })

        title("加载驱动和连接测试")
        nameField.text = propertiesComponent.getValue(DS_NAME)
        commField.text = propertiesComponent.getValue(DS_COMM)
        hostField.text = propertiesComponent.getValue(DS_HOST)
        portField.text = propertiesComponent.getValue(DS_PORT)
        userField.text = propertiesComponent.getValue(DS_USER)
        passField.text = propertiesComponent.getValue(DS_PASS)

        loadButton.addActionListener {
            unloadDriver()
            val loader = URLClassLoader(arrayOf<URL>(File(driverField.text).toURI().toURL()), javaClass.classLoader)
            val driverClass = typeBox.item.driver
            Class.forName(driverClass, true, loader)
            val driver = Class.forName(driverClass, true, loader).getDeclaredConstructor().newInstance() as Driver
            DriverManager.registerDriver(DriverDelegate(driver))
            Messages.showMessageDialog("驱动加载成功！", "加载驱动", typeBox.item.icon)
        }
        unloadButton.addActionListener {
            unloadDriver()
            Messages.showMessageDialog("驱动卸载载成功！", "卸载驱动", typeBox.item.icon)
        }

        connectionButton.addActionListener {
            val ds = Datasource(typeBox.item, driverField.text, nameField.text, commField.text, hostField.text, portField.text, userField.text, passField.text)
            CoroutineScope(Dispatchers.IO).launch {
                var connection: Connection? = null
                setErrorText("连接中。。。")
                try {
                    connection = DriverManager.getConnection(ds.getUrl(), ds.user, ds.password)
                    setErrorText("连接成功")
                } catch (e: Exception) {
                    setErrorText(e.message)
                } finally {
                    connection?.run { close() }
                }
            }
        }
        val centerPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent("数据源类型", typeBox)
            .addLabeledComponent("名称:", nameField)
            .addLabeledComponent("描述:", commField)
            .addLabeledComponent("地址:", hostField)
            .addLabeledComponent("端口:", portField)
            .addLabeledComponent("用户:", userField)
            .addLabeledComponent("密码:", passField)
            .addLabeledComponentFillVertically("驱动:", driverField)
            .addComponentFillVertically(JPanel(FlowLayout(FlowLayout.LEFT)).apply {
                this.layout = BoxLayout(this, BoxLayout.X_AXIS)
                add(loadButton)
                add(unloadButton)
                add(connectionButton)
            }, 5)
            .panel
        centerPanel(centerPanel)

        setOkOperation {
            dispose()
            dialogWrapper.close(DialogWrapper.OK_EXIT_CODE)
            propertiesComponent.setValue(DS_DRIVER, driverField.text)
            propertiesComponent.setValue(DS_NAME, nameField.text)
            propertiesComponent.setValue(DS_COMM, commField.text)
            propertiesComponent.setValue(DS_HOST, hostField.text)
            propertiesComponent.setValue(DS_PORT, portField.text)
            propertiesComponent.setValue(DS_USER, userField.text)
            propertiesComponent.setValue(DS_PASS, passField.text)
        }
    }

    private fun unloadDriver() {
        val drivers = DriverManager.getDrivers()
        val list = mutableListOf<DriverDelegate>()
        while (drivers.hasMoreElements()) {
            val driver = drivers.nextElement()
            if (driver is DriverDelegate) {
                list.add(driver)
            }
        }
        list.forEach { DriverManager.deregisterDriver(it) }
    }
}