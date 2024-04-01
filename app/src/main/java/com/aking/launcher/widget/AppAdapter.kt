package com.aking.launcher.widget

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.aking.common.utils.CommonViewHolder
import com.aking.launcher.databinding.ItemAppBinding
import com.aking.launcher.model.AppMetaData
import com.aking.launcher.model.AppMetaDiff

class AppAdapter : ListAdapter<AppMetaData, CommonViewHolder>(AppMetaDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        return CommonViewHolder.inflate(ItemAppBinding::inflate, parent, false)
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        val item = getItem(holder.adapterPosition)
        holder.getBinding<ItemAppBinding>().apply {
            appIcon.setImageDrawable(item.mIcon)
            appName.text = item.displayName
            appItem.setOnClickListener {
            }
        }
    }
}