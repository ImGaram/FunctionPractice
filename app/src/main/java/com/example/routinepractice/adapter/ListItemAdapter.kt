package com.example.routinepractice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.routinepractice.databinding.ItemListBinding
import com.example.routinepractice.item.ListItem

class ListItemAdapter(private val list: List<ListItem>): RecyclerView.Adapter<ListItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(listItem: ListItem) {
            binding.titleText.text = listItem.title
            binding.nameText.text = listItem.name
            binding.color.setBackgroundColor(listItem.color)
        }
    }
}