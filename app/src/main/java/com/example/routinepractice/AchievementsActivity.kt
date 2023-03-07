package com.example.routinepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.routinepractice.databinding.ActivityAchievementsBinding

class AchievementsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAchievementsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAchievementsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressBar.progress = 0    // 초기 설정

        var click = 0
        val liveData = MutableLiveData<Int>()
        binding.countBtn.setOnClickListener {
            liveData.value = click++
        }

        // 카운트 감지(observe)
        liveData.observe(this) {
            binding.progressText.text = "$it/100"
            binding.progressBar.progress = it

            if (it == 100) {
                binding.countBtn.visibility = View.GONE
                Toast.makeText(this, "조건을 충족하였습니다!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}