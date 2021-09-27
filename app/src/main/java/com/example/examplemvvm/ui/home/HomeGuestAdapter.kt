package com.example.examplemvvm.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.examplemvvm.databinding.ItemGuestBinding
import com.example.examplemvvm.model.Guest

class HomeGuestAdapter : RecyclerView.Adapter<HomeGuestAdapter.GuestViewHolder>() {

    inner class GuestViewHolder(val binding: ItemGuestBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object: DiffUtil.ItemCallback<Guest>()
    {
        override fun areItemsTheSame(oldItem: Guest, newItem: Guest) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Guest, newItem: Guest) = oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GuestViewHolder(
        ItemGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        with(holder)
        {
            val name = differ.currentList[position].name
            val presence = differ.currentList[position].presence

            binding.textGuest.text = name
            binding.textGuestStatus.text = presence.name
        }
    }

    override fun getItemCount() = differ.currentList.size
}