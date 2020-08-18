package com.hyunjung.munuhmunhwa

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyunjung.munuhmunhwa.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.item_contents.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var contentItems = ArrayList<ContentItem>()
    private lateinit var adapter: ContentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        adapter = ContentAdapter(contentItems)
        binding.rvContent.layoutManager = LinearLayoutManager(this)
        binding.rvContent.adapter = adapter

        binding.btnFilter.isEnabled = true

        // 필터 버튼 클릭 시 실행
        binding.btnFilter.setOnClickListener {
            Toast.makeText(this, "버튼이 눌렸습니다", Toast.LENGTH_SHORT).show()
            var resultIntent = Intent(this, ResultActivity::class.java)
            startActivity(resultIntent)
            finish()
        }
    }

    override fun onBackPressed() {
        var dialog = AlertDialog.Builder(this)
            .setTitle("확인")
            .setMessage("앱을 종료 할까요?")
            .setNegativeButton("취소", object: DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1:Int) {
                    // 취소 버튼을 눌렀을 경우
                    p0?.cancel()
                }
            })
            .setPositiveButton("종료"){
                    dialogInterface, i ->
                finish()
            }
            .create()

        dialog.show()
    }



    //해당 URL로 이동한다.
    /**
     * var startWebIntent = Intent(Intent.ACTION_VIEW)
     * StartWebInten.data = Uri.parse("해당 URL 적기")
     * startActivity(startWebIntent)*/
}
