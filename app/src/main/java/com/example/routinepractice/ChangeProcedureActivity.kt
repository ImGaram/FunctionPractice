package com.example.routinepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.routinepractice.adapter.ChangeProcedureAdapter
import com.example.routinepractice.database.ProcedureDatabase
import com.example.routinepractice.databinding.ActivityChangeProcedureBinding
import com.example.routinepractice.item.ProcedureItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChangeProcedureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangeProcedureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChangeProcedureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tempList = mutableListOf<ProcedureItem>()
        tempList.add(ProcedureItem("title1", "name1", false))
        tempList.add(ProcedureItem("title2", "name2", false))
        tempList.add(ProcedureItem("title3", "name3", false))
        tempList.add(ProcedureItem("title4", "name4", false))
        tempList.add(ProcedureItem("title5", "name5", false))

        binding.checkRecyclerView.layoutManager = LinearLayoutManager(this)
//        CoroutineScope(Dispatchers.IO).launch {
//            ProcedureDatabase.getInstance(applicationContext)?.procedureDao()?.deleteAll()
//            binding.checkRecyclerView.adapter = ChangeProcedureAdapter(ProcedureDatabase.getInstance(applicationContext)!!, tempList)
//        }
        binding.checkRecyclerView.adapter = ChangeProcedureAdapter(ProcedureDatabase.getInstance(applicationContext)!!, tempList)
    }
}