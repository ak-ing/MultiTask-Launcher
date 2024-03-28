package com.aking.common.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aking.common.extended.TAG

/**
 * Created by Rick on 2023-07-08  23:10
 * Description:
 */
abstract class LifecycleActivity : AppCompatActivity() {

    private val tag = TAG
    private var lifecycleLog = false

    /**
     * 生命周期日志开关
     */
    protected fun lifecycleLogEnable(enable: Boolean) {
        lifecycleLog = enable
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (lifecycleLog) Log.v(tag, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        if (lifecycleLog) Log.v(tag, "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        if (lifecycleLog) Log.v(tag, "onRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (lifecycleLog) Log.v(tag, "onSaveInstanceState")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (lifecycleLog) Log.v(tag, "onNewIntent")
    }

    override fun onResume() {
        super.onResume()
        if (lifecycleLog) Log.v(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        if (lifecycleLog) Log.v(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        if (lifecycleLog) Log.v(tag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (lifecycleLog) Log.v(tag, "onDestroy")
    }


}