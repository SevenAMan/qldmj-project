package org.qldmj.entity

import org.qldmj.copy.DeepCopyable

data class GitUser(
    val id: Int,
    val login: String,
    var url: String,
): DeepCopyable
