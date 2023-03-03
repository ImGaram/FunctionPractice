package com.example.routinepractice

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.routinepractice.databinding.ActivityAddListBinding
import com.example.routinepractice.room.ListDatabase
import com.example.routinepractice.room.ListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import yuku.ambilwarna.AmbilWarnaDialog

class AddListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var colorCode = Color.parseColor("#ff0000")
        binding.changeColor.setOnClickListener {
            val colorPicker = AmbilWarnaDialog(this, colorCode, object :AmbilWarnaDialog.OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog?) {}

                override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                    colorCode = color
                    binding.changeColor.setBackgroundColor(colorCode)
                }
            })
            colorPicker.show()
        }

        binding.finish.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val db = ListDatabase.getInstance(applicationContext)
                db!!.listDao().insert(ListItem(binding.title.text.toString(), binding.name.text.toString(), colorCode))
                finish()
            }
        }
    }
}