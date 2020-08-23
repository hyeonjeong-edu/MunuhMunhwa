package com.hyunjung.munuhmunhwa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyunjung.munuhmunhwa.databinding.ActivityResultBinding


class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    var adapter:ContentAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)

        var age = getIntent().getIntExtra("age", 1)
        var gender = getIntent().getIntExtra("gender", 6)
        var place = getIntent().getStringExtra("place")
        var genre = getIntent().getIntExtra("genre", 8)
        var fee = getIntent().getIntExtra("fee", 12)

        // 데이터 추가
        Thread({
            var apiContent = ApiContent()
            adapter = apiContent.main()
            runOnUiThread {
                binding.rvContent.adapter = adapter
            }
        }).start()

        var linearLayoutManager = LinearLayoutManager(this)
        binding.rvContent.layoutManager = linearLayoutManager

    }
}
