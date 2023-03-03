package com.example.routinepractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.routinepractice.adapter.ListItemAdapter
import com.example.routinepractice.databinding.ActivityMainBinding
import com.example.routinepractice.room.ListDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addBtn.setOnClickListener {
            startActivity(Intent(this, AddListActivity::class.java))
        }

        val db = ListDatabase.getInstance(applicationContext)!!
        // list에 변화가 생기면 UI를 갱신해줌
        binding.listRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        db.listDao().getAll().observe(this@MainActivity) {
            binding.listRecyclerView.adapter = ListItemAdapter(it)
        }
    }
}