package com.aking.common.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class CommonViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun <T : ViewBinding> getBinding(): T {
        return binding as T
    }

    fun setOnClickListener(listener: View.OnClickListener) {
        binding.root.setOnClickListener(listener)
    }

    companion object {
        fun inflate(
            inflater: (LayoutInflater, ViewGroup, Boolean) -> ViewBinding,
            parent: ViewGroup,
            attachToRoot: Boolean
        ): CommonViewHolder {
            val binding = inflater.invoke(LayoutInflater.from(parent.context), parent, attachToRoot)
            return CommonViewHolder(binding)
        }
    }
}

