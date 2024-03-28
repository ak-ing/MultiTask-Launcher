package com.aking.common.base

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.aking.common.extended.binding

/**
 * Created by Rick on 2023-01-30  20:22.
 * Description:
 */
abstract class BaseFragment<V : ViewBinding>(inflate: (View) -> V) : LifecycleFragment() {

    protected val binding: V by binding(inflate)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initView()
        binding.initData()
    }

    protected open fun V.initView() {}
    protected open fun V.initData() {}

}