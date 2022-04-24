package com.san.archapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.san.archapp.data.entity.CommonItem
import com.san.archapp.databinding.ItemListBinding

class CommonListAdapter(val callBack: (data: CommonItem) -> Unit) :
    ListAdapter<CommonItem, RecyclerView.ViewHolder>(UserListDiffCallBack()) {

    class UserListDiffCallBack : DiffUtil.ItemCallback<CommonItem>() {
        override fun areItemsTheSame(oldItem: CommonItem, newItem: CommonItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CommonItem, newItem: CommonItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListItem(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ListItem).onBind(getItem(position))
    }


    inner class ListItem(var binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: CommonItem) {
            with(binding) {
                tvId.text = data.id.toString()
                tvItemName.text = data.title

                this.root.setOnClickListener {
                    callBack.invoke(data)
                }
            }
        }
    }
}