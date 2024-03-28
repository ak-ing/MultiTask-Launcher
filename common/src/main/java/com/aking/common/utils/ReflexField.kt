package com.aking.common.utils

import android.util.Log
import java.lang.reflect.Field

class ReflexField<T>(private val clazz: Class<*>, fieldName: String) {
    private var field: Field? = null

    constructor(clazzName: String, fieldName: String) : this(Class.forName(clazzName), fieldName)

    init {
        try {
            field = clazz.getDeclaredField(fieldName)
        } catch (_: Exception) {
        }

        if (field == null) {
            Log.e("Reflex", "${clazz.name} have no field \" $fieldName \"!")
        }
    }

    fun setField(obj: Any? = null, value: T?) {
        field?.isAccessible = true
        field?.set(obj, value)
    }

    fun getFieldValue(obj: Any? = null): T? {
        field?.isAccessible = true
        return field?.get(obj) as? T
    }
}