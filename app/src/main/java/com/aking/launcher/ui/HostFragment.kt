package com.aking.launcher.ui

import com.aking.common.base.BaseFragment
import com.aking.launcher.base.FragmentPageAdapter
import com.aking.launcher.databinding.FragmentHostBinding
import java.util.function.Supplier

class HostFragment : BaseFragment<FragmentHostBinding>(FragmentHostBinding::bind) {

    private val fragmentCreators = listOf(Supplier { NegativeScreen() }, Supplier { AppsScreen() })

    override fun FragmentHostBinding.initView() {
        workSpace.adapter = FragmentPageAdapter(this@HostFragment, fragmentCreators)
        workSpace.setCurrentItem(1, false)
    }

}