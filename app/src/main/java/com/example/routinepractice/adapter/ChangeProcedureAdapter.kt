package com.example.routinepractice.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.routinepractice.database.ProcedureDatabase
import com.example.routinepractice.databinding.ItemCkeckBinding
import com.example.routinepractice.item.ProcedureItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Collections

class ChangeProcedureAdapter(private val db: ProcedureDatabase, private val list: MutableList<ProcedureItem>): RecyclerView.Adapter<ChangeProcedureAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemCkeckBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], position)

        checkCheckBoxChecked(list[position], holder.checkBox, holder.adapterPosition)
    }

    inner class ViewHolder(private val binding: ItemCkeckBinding): RecyclerView.ViewHolder(binding.root) {
        val checkBox = binding.completeAble
        val title = binding.titleTextCheck
        val name = binding.nameTextCheck

        fun bind(procedureItem: ProcedureItem, position: Int) {
            binding.nameTextCheck.text = procedureItem.name
            binding.titleTextCheck.text = procedureItem.title
            binding.completeAble.isChecked = procedureItem.checkAble

            binding.completeAble.setOnClickListener {
                checkBoxLogic(procedureItem, checkBox, adapterPosition, position)
            }
        }
    }

    // 체크박스가 true로 설정이 이미 되어있을때의 해당 아이템을 리사이클러뷰의 맨 뒤로 보내기 위한 로직
    private fun checkCheckBoxChecked(procedureItem: ProcedureItem, checkBox: CheckBox, position: Int) {
        if (checkBox.isChecked) {
            list.removeAt(position)
            list.add(list.lastIndex, procedureItem)
            notifyItemMoved(position, list.lastIndex)
        }
    }

    private fun checkBoxLogic(procedureItem: ProcedureItem, checkBox: CheckBox, adapterPosition: Int, position: Int) {
        if (checkBox.isChecked) {   // 체크박스가 체크됨
            CoroutineScope(Dispatchers.IO).launch {
                val updateItem = ProcedureItem(procedureItem.title, procedureItem.name, checkBox.isChecked)
                val last = list.lastIndex

                db.procedureDao().update(updateItem)
                CoroutineScope(Dispatchers.Main).launch {
                    Collections.swap(list, adapterPosition, last)

                    notifyItemMoved(adapterPosition, last)
                    Log.d("TAG", "checkBoxLogic pos: $adapterPosition")
                    Log.d("TAG", "checkBoxLogic list: $last")
                }
            }
        } else {    // 체크되지 않음
            CoroutineScope(Dispatchers.IO).launch {
                val updateItem = ProcedureItem(procedureItem.title, procedureItem.name, checkBox.isChecked)
                val last = list.lastIndex

                db.procedureDao().update(updateItem)
                CoroutineScope(Dispatchers.Main).launch {
                    Collections.swap(list, last, position)

                    notifyItemMoved(last, position)
                }
            }
        }
    }
}