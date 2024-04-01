package com.aking.launcher.base

import android.view.View
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0
import kotlin.reflect.KProperty1

abstract class BaseListAdapter<T, VH : ViewHolder>(diffCallback: ItemCallback<T>) :
    ListAdapter<T, VH>(diffCallback) {

    private var listener: ((holder: VH, position: Int) -> Unit)? = null

    fun setOnItemClickListener(prop:KProperty1<T, View>,listener: (holder: VH, position: Int) -> Unit) {
        prop.
        this.listener = listener
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            listener?.invoke(holder, position)
        }
    }
}