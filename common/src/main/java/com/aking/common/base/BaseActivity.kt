package com.aking.common.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.aking.common.extended.contentView

/**
 * Created by Rick on 2023-01-30  19:24.
 * Description:
 */
abstract class BaseActivity<V : ViewBinding>(inflate: (LayoutInflater) -> V) : LifecycleActivity() {

    protected val binding: V by contentView(inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.initView()
        binding.initData()
    }

    protected open fun V.initView() {}
    protected open fun V.initData() {}

}
