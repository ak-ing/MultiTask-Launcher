package com.aking.launcher.model

import android.content.ComponentName
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import java.util.function.Consumer

data class AppMetaData(
    var displayName: String,
    var componentName: ComponentName,
    var mIcon: Drawable,
    var mLaunchCallback: Consumer<View>? = null, //启动
    var mAlternateLaunchCallback: Consumer<Context>? = null, //交叉启动
    var isSystemApp: Boolean = false
)

object AppMetaDiff : DiffUtil.ItemCallback<AppMetaData>() {
    override fun areItemsTheSame(oldItem: AppMetaData, newItem: AppMetaData): Boolean {
        return oldItem.componentName == newItem.componentName
    }

    override fun areContentsTheSame(oldItem: AppMetaData, newItem: AppMetaData): Boolean {
        return oldItem == newItem
    }

}