package com.aking.launcher

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.aking.common.base.BaseActivity
import com.aking.launcher.databinding.ActivityLauncherBinding

class Launcher : BaseActivity<ActivityLauncherBinding>(ActivityLauncherBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}