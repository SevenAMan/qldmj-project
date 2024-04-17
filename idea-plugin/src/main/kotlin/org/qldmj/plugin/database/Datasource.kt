package org.qldmj.plugin.database

/**
 * 数据源配置
 */
data class Datasource(
    val type: DatabaseType = DatabaseType.MYSQL,
    val driverPath: String,
    var name: String,
    val comment: String,
    var host: String,
    var port: String,
    var user: String,
    var password: String,
) {
    fun getUrl(): String {
        return String.format(type.urlTemp, host, port)
    }
}