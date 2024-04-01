package com.aking.launcher.utl

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.aking.launcher.R


object ActivityUtils {
    fun startActivitySafely(view: View, intent: Intent?, vararg flags: Int): Boolean {
        var success = false
        if (intent != null) {
            if (flags.isNotEmpty()) {
                for (flag: Int in flags) {
                    intent.addFlags(flag)
                }
            } else {
                intent.addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                            or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                )
            }
            try {
                val startX = view.width / 2
                val startY = view.height / 2
                //view.getContext().startActivity(intent, ActivityOptions.makeScaleUpAnimation(view, startX, startY, 0, 0).toBundle());
                val compat = ActivityOptionsCompat.makeCustomAnimation(
                    view.context,
                    R.anim.enter_scale,
                    R.anim.exit_scale
                )
                view.context.startActivity(intent, compat.toBundle())
                //view.getContext().startActivity(intent, ActivityOptions.makeCustomAnimation(view.getContext(), R.anim.enter_scale, R.anim.exit_scale).toBundle());
                success = true
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return success
    }

    /**
     * @param intent 启动组件意图
     */
    fun startActivitySafely(context: Context, intent: Intent?, vararg flags: Int): Boolean {
        var success = false
        if (intent != null) {
            if (flags.isNotEmpty()) {
                for (flag: Int in flags) {
                    intent.addFlags(flag)
                }
            } else {
                intent.addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                            or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                )
            }
            try {
                context.startActivity(intent)
                success = true
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
        return success
    }
}