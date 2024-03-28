package com.aking.common.extended

/**
 * Created by AK on 2024-03-28.
 * Description: ViewBinding拓展
 */
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KProperty

/**
 * Activity初始化ViewBinding拓展，自动调用 [Activity.setContentView]
 */
fun <A : AppCompatActivity, T : ViewBinding> AppCompatActivity.contentView(
    inflate: (LayoutInflater) -> T
): ContentViewBindingDelegate<A, T> = ContentViewBindingDelegate(inflate)

/**
 * Fragment初始化ViewBinding拓展，返回binding,自动销毁
 */
fun <F : Fragment, T : ViewBinding> Fragment.binding(
    inflate: (View) -> T
): FragmentViewBindingDelegate<F, T> = FragmentViewBindingDelegate(inflate)


/**
 * 延迟初始化布局绑定，自动调用 [Activity.setContentView]
 */
class ContentViewBindingDelegate<in A : AppCompatActivity, out T : ViewBinding>(
    private val inflate: (LayoutInflater) -> T
) {

    private lateinit var binding: T

    operator fun getValue(activity: A, property: KProperty<*>): T {
        if (this::binding.isInitialized) {
            return binding
        }
        binding = inflate.invoke(activity.layoutInflater)
        activity.setContentView(binding.root)
        return binding
    }
}


/**
 * 绑定fragment布局View，返回binding,自动销毁
 */
class FragmentViewBindingDelegate<in F : Fragment, out T : ViewBinding>(
    private val inflate: (View) -> T
) {

    private var binding: T? = null

    operator fun getValue(fragment: F, property: KProperty<*>): T {
        binding?.let { return it }

        val view = checkNotNull(fragment.view) {
            "The view of the fragment has not been initialized or has been destroyed!"
        }

        binding = inflate.invoke(view)

        fragment.parentFragmentManager.registerFragmentLifecycleCallbacks(Clear(fragment), false)

        return requireNotNull(binding)
    }

    inner class Clear(private val thisRef: F) : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
            if (thisRef === f) {
                binding = null
                fm.unregisterFragmentLifecycleCallbacks(this)
            }
        }
    }

}