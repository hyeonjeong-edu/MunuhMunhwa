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
        binding.rvContent.layoutManager = LinearLayoutManager(this)

        // 데이터 추가
        Thread({
            var apiContent = ApiContent()
            adapter = apiContent.main()
            runOnUiThread {
                binding.rvContent.adapter = adapter
            }
        }).start()

        binding.btnSearch.isEnabled = true

        // 검색 버튼 클릭 시 실행
        binding.btnSearch.setOnClickListener {
            var filterIntent = Intent(this, FilterActivity::class.java)
            startActivity(filterIntent)
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
