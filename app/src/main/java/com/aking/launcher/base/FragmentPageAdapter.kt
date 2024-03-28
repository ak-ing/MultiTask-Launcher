package com.aking.launcher.base

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.function.Supplier

class FragmentPageAdapter(
    fragment: Fragment,
    private val fragmentCreators: List<Supplier<out Fragment>>
) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = fragmentCreators.size
    override fun createFragment(position: Int): Fragment = fragmentCreators[position].get()
}