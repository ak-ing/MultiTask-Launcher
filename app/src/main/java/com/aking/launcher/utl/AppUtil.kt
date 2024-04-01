package com.aking.launcher.utl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.LauncherApps
import android.content.pm.PackageManager
import android.util.Log
import com.aking.launcher.model.AppMetaData
import com.aking.launcher.model.LaunchAppInfo

object AppUtil {

    private fun getLauncherApps(
        launcherApps: LauncherApps,
        context: Context
    ): LaunchAppInfo {
        val availableActivities =
            launcherApps.getActivityList(null, android.os.Process.myUserHandle())
        val launchAblesMap: MutableMap<ComponentName, AppMetaData> =
            HashMap(availableActivities.size)
        availableActivities
            .filter {
                context.packageName != it.componentName.packageName
            }
            .forEach { info ->
                val componentName = info.componentName
                val packageName = componentName.packageName

//                if (mFilter?.hiddeApp(packageName) != true) {
                val intent = Intent(Intent.ACTION_MAIN).apply {
                    component = componentName
                    addCategory(Intent.CATEGORY_LAUNCHER)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }

                val pm = context.applicationContext.packageManager
                val pkgInfo = pm.getPackageInfo(packageName, PackageManager.GET_CONFIGURATIONS)
                val flag = pkgInfo?.applicationInfo?.flags ?: 0
                val isSystemApp =
                    flag.and(ApplicationInfo.FLAG_SYSTEM) != 0 || flag.and(ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0

                /*Log.e("App", "app : $packageName  " +
                        "is system app : ${flag.and(ApplicationInfo.FLAG_SYSTEM) != 0} " +
                        "updated: ${flag.and(ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0} " +
                        "external app: ${flag.and(ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0}")*/
                val icon = info.getBadgedIcon(320)
                val metaData = AppMetaData(
                    info.label as String, componentName, icon, { view ->
                        //LauncherUtils.launchApp(context, intent)
                        ActivityUtils.startActivitySafely(view, intent)
                    },
                    { context ->
                        // 返回系统中所有MainActivity带有Intent.CATEGORY_INFO 和 Intent.CATEGORY_LAUNCHER的intent
                        val packageLaunchIntent =
                            context.packageManager.getLaunchIntentForPackage(packageName)
                        launchApp(
                            context,
                            packageLaunchIntent ?: intent
                        )
                    },
                    isSystemApp = isSystemApp
                )
                launchAblesMap[componentName] = metaData
//                }
            }
        return LaunchAppInfo(launchAblesMap)
    }

    fun launchApp(context: Context, intent: Intent?) {
        Log.i("Launch", "----------->> launchApp  : $intent")
        ActivityUtils.startActivitySafely(context, intent)
    }

}

