package com.aking.common.utils

import android.util.Log
import java.lang.reflect.Method
import java.util.Arrays

class ReflexMethod(private var methodName: String) {
    private var method: Method? = null
    private var obj: Any? = null
    private lateinit var clazz: Class<*>
    private var params: Array<Any?>? = null
    private var paramTypes: Array<Class<*>>? = null

    init {
        check(methodName.isNotEmpty()) {
            "Method name can't be empty!!"
        }
    }

    fun setupClass(cls: String): ReflexMethod {
        runCatching { clazz = Class.forName(cls) }
        return this
    }

    /**
     * 指定查找方法的类
     * @param cls
     */
    fun setupClass(cls: Class<*>): ReflexMethod {
        clazz = cls
        return this
    }

    /**
     * 类函数不用传入对象，指定操作对象，反射的函数时该对象的成员
     * @param
     */
    fun target(obj: Any? = null): ReflexMethod {
        obj?.also {
            setupClass(obj.javaClass)
        }
        this.obj = obj
        return this
    }

    /**
     * 复杂参数类型适配， 简单的函数调用可以省略这一步，
     * 直接传入参数后可以自动适配参数类型查找
     * @param paramClasses 函数参数类型表
     */
    fun paramClasses(vararg paramClasses: Class<*>): ReflexMethod {
        if (paramClasses.isEmpty()) {
            return this
        }
        val types = mutableListOf<Class<*>>()
        for (i in paramClasses.indices) {
            types.add(paramClasses[i])
        }

        if (types.size > 0) {
            paramTypes = types.toTypedArray()
        }
        return this
    }

    /**
     * 传入函数参数，简单
     * @param params 函数参数
     */
    fun params(vararg params: Any): ReflexMethod {
        if (params.isEmpty()) {
            return this
        }
        method = null
        val len = params.size
        this.params = arrayOfNulls(len)
        val clazzs = mutableListOf<Class<*>>()
        val types = mutableListOf<Class<*>>()
        for (i in params.indices) {
            this.params!![i] = params[i]
            val cls = ReflexField<Class<*>>(params[i].javaClass, "TYPE").getFieldValue()
            types.add(cls ?: params[i].javaClass)
            clazzs.add(params[i].javaClass)
        }

        if (paramTypes == null) {
            if (types.size > 0) {
                paramTypes = types.toTypedArray()
                findMethod()
            }

            if (method == null) {
                paramTypes = clazzs.toTypedArray()
                findMethod()
            }
        } else {
            findMethod()
        }

        return this
    }

    private fun findMethod() {
        method = if (paramTypes == null)
            clazz.getDeclaredMethod(methodName)
        else
            clazz.getDeclaredMethod(methodName, *paramTypes!!)
    }

    fun <Result> call(): Result? {
        if (method == null) {
            findMethod()
        }

        assert(method != null) {
            "${clazz.name} has no method \" $methodName \"!!"
        }

        method?.isAccessible = true
        Log.e(
            "Reflex",
            "obj: $obj  param  ${Arrays.toString(params)}  param Class: ${Arrays.toString(paramTypes)}"
        )
        val result = (if (params == null) {
            method?.invoke(obj)
        } else {
            method?.invoke(obj, *params!!)
        })
        return result?.let { it as Result }
    }
}