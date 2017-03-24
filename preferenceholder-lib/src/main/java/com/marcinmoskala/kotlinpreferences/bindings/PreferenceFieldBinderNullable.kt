@file:Suppress("UNCHECKED_CAST")

package com.marcinmoskala.kotlinpreferences.bindings

import android.content.SharedPreferences
import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

internal class PreferenceFieldBinderNullable<T : Any>(val clazz: KClass<T>, val type: Type, val key: String?) : ReadWriteProperty<PreferenceHolder, T?> {

    val pref: SharedPreferences
        get() = PreferenceHolder.preferences

    override operator fun getValue(thisRef: PreferenceHolder, property: KProperty<*>): T? = readValue(property)

    override fun setValue(thisRef: PreferenceHolder, property: KProperty<*>, value: T?) {
        if (value == null) {
            removeValue(property)
        } else {
            saveValue(property, value)
        }
    }

    private fun readValue(property: KProperty<*>): T? {
        val key = getKey(property)
        if (!pref.contains(key)) return null
        return pref.getByKey(key)
    }

    private fun SharedPreferences.getByKey(key: String): T? = when (clazz.simpleName) {
        "Long" -> getLong(key, -1L) as? T
        "Int" -> getInt(key, -1) as? T
        "String" -> getString(key, null) as? T
        "Boolean" -> getBoolean(key, false) as? T
        "Float" -> getFloat(key, -1.0F) as T
        else -> getString(key, null)?.fromJson<T>(type) as? T
    }

    private fun removeValue(property: KProperty<*>) {
        pref.edit()
                .remove(getKey(property))
                .apply()
    }

    private fun saveValue(property: KProperty<*>, value: T) {
        pref.edit().apply { putValue(clazz, value, getKey(property)) }.apply()
    }

    private fun getKey(property: KProperty<*>) = key ?: "${property.name}Key"
}