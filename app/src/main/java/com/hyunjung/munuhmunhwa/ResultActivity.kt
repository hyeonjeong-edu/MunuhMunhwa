package com.hyunjung.munuhmunhwa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyunjung.munuhmunhwa.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)

        var contentItems = ArrayList<ContentItem>()
        for (idx in 1.. 10){
            var contentItem = ContentItem("https://www.culture.go.kr/upload/rdf/20/08/rdf_2020080621275658894.jpg" ,"Title $idx", "Subtitle $idx")
            contentItems.add(contentItem)
        }

        var adapter = ContentAdapter(contentItems)
        var linearLayoutManager = LinearLayoutManager(this)
        binding.rvContent.layoutManager = linearLayoutManager
        binding.rvContent.adapter = adapter
    }

}
