package com.aking.launcher.model

import android.content.ComponentName
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import java.util.function.Consumer

data class AppMetaData(
    var displayName: String,
    var componentName: ComponentName,
    var mIcon: Drawable,
    var mLaunchCallback: Consumer<View>? = null, //启动
    var mAlternateLaunchCallback: Consumer<Context>? = null, //交叉启动
    var isSystemApp: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        return if (other !is AppMetaData) {
            false
        } else {
            other.componentName == componentName
        }
    }

    override fun hashCode(): Int {
        return componentName.hashCode()
    }

    override fun toString(): String {
        return "mDisplayName : $displayName, $componentName"
    }
}