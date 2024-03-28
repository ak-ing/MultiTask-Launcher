package com.aking.common.extended

/**
 * Created by Rick on 2023-11-16  17:04.<p>
 *
 * Description: Any类拓展
 */

/**
 * class TAG
 */
val Any.TAG get():String = this::class.java.simpleName

val Any.TAG_C get() = this::class.java.simpleName + ":" + Integer.toHexString(hashCode())
