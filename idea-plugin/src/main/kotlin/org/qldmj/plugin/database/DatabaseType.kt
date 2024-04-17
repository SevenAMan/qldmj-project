package org.qldmj.plugin.database

import com.intellij.icons.AllIcons
import javax.swing.Icon

enum class DatabaseType(name: String, val driver: String, val icon: Icon, val urlTemp: String) {

    MYSQL("MySQL", "com.mysql.cj.jdbc.Driver", AllIcons.Providers.Mysql, "jdbc:mysql://%s:%s"),
    ORACLE("Oracle", "oracle.jdbc.OracleDriver", AllIcons.Providers.Oracle, "jdbc:oracle:thin@//%s:%s")
    ;

    override fun toString(): String {
        return name
    }
}