package com.aking.launcher.model

import android.content.ComponentName

data class LaunchAppInfo(val mLaunchAbles: Map<ComponentName, AppMetaData> = mutableMapOf()) {
    fun isEmpty() = mLaunchAbles.isEmpty()

    fun getAppMetaData(componentName: ComponentName): AppMetaData? {
        return mLaunchAbles.getOrDefault(componentName, null)
    }

    fun isLauncherActivity(componentName: ComponentName?) = mLaunchAbles.contains(componentName)

    fun getLaunchAbleComponentsList(): List<AppMetaData> {
        return ArrayList(mLaunchAbles.values)
    }
}