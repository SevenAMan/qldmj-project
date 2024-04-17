package org.qldmj.plugin.database

import java.sql.Driver

/**
 * 驱动委托，用于卸载时的判断
 */
class DriverDelegate(driver: Driver) : Driver by driver