package com.san.archapp.ui.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.san.archapp.data.entity.Data
import com.san.archapp.databinding.ItemUserBinding

class UserListAdapter(val callBack: (data: Data) -> Unit) :
    ListAdapter<Data, RecyclerView.ViewHolder>(UserListDiffCallBack()) {

    class UserListDiffCallBack : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemUsers(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemUsers).onBind(getItem(position))
    }


    inner class ItemUsers(var binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(user: Data) {
            with(binding) {

                ivUserStatus.imageTintList = ColorStateList.valueOf(getStatusColor(user.status))

                tvUserName.text = user.name
                tvUserEmail.text = user.email
                tvUserGender.text = user.gender
                etComment.setText(user.comments)
                root.setOnClickListener {
                    callBack.invoke(user)
                }

                tilComment.setEndIconOnClickListener {
                    if (etComment.text.isNullOrBlank()) {
                        etComment.error = "should not be empty"
                        return@setEndIconOnClickListener
                    }
                    user.comments = etComment.text.toString()
                    callBack.invoke(user)
                }
            }
        }
    }

    private fun getStatusColor(state: String? = null): Int {
        return when (state) {
            "active" -> Color.GREEN
            "inactive" -> Color.RED
            else -> Color.GRAY
        }
    }
}