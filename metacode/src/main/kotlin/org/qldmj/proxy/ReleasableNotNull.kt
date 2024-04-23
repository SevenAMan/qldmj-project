package org.qldmj.proxy

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0

fun <T : Any> releasableNotNull() = ReleasableNotNull<T>()

fun <T> KProperty0<T>.releasableNotNull() {
    val delegate = getDelegate()
    if (delegate is ReleasableNotNull<*> && delegate.isInitialized()) {
        delegate.release()
    }
}

class ReleasableNotNull<T : Any> : ReadWriteProperty<Any?, T> {

    private var value: T? = null

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return this.value ?: throw IllegalStateException("${property.name} not initialized")
    }

    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
    }

    fun isInitialized(): Boolean {
        return value != null
    }

    fun release() {
        this.value = null
    }
}