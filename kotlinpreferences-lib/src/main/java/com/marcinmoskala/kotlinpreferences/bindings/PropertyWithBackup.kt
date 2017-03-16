package com.marcinmoskala.kotlinpreferences.bindings

import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import kotlin.concurrent.thread
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

internal class PropertyWithBackup<T : Any>(clazz: KClass<T>, default: T?, key: String?) : PreferenceFieldBinder<T>(clazz, default, key) {

    var field: T? = default

    override operator fun getValue(thisRef: PreferenceHolder, property: KProperty<*>): T =
            field ?: super.getValue(thisRef, property).apply { field = this }

    override fun setValue(thisRef: PreferenceHolder, property: KProperty<*>, value: T) {
        field = value
        thread {
            super.setValue(thisRef, property, value)
        }
    }
}