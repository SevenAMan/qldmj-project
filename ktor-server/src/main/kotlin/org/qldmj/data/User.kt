package org.qldmj.data

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Database

@Serializable
data class User1(var name: String, var id: Int, var comment: String)

object User : IntIdTable() {
    val name: Column<String> = varchar("name", 50)
    val password: Column<String> = varchar("password", 50)
    val amount: Column<Int> = integer("amount")
}

fun connectionDatabase(host: String, port: String, database: String, user: String, password: String) =
    Database.connect(
        "jdbc:mysql://$host:$port/$database",
        driver = "com.mysql.cj.jdbc.Driver",
        user = user,
        password = password
    )

fun multiUser() = mutableListOf(
    User1("zs", 1, "user zs"),
    User1("js", 2, "user json"),
    User1("td", 3, "IFT hero")
)
