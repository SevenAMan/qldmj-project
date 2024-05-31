package org.qldmj.data

import java.util.UUID

enum class AccessLevel {
    USER, ADMIN
}

data class UserSession(val uuid: UUID, val level: AccessLevel)