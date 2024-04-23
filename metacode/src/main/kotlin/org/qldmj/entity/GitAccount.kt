package org.qldmj.entity

import org.qldmj.copy.DeepCopyable
import org.qldmj.copy.deepCopy

data class GitAccount(
    var id: Int,
    var user: GitUser,
) : DeepCopyable

fun main() {
    val acct = GitAccount(1, GitUser(1, "one", "/one"))
    val acct1 = acct.deepCopy()
    acct.user.url = "/one/one"
    println(acct)
    println(acct1)
}