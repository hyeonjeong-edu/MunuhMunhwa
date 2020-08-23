package com.hyunjung.munuhmunhwa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.hyunjung.munuhmunhwa.databinding.ActivityFilterBinding

class FilterActivity : AppCompatActivity() {
    lateinit var binding: ActivityFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter)

        //완료 버튼이 눌리면 전체 값 전송해서 추천 이벤트 출력하기
        binding.btDone.setOnClickListener {
            // 나이 라디오버튼 결과
            var age = binding.rgAge.checkedRadioButtonId
            // 성별 라디오버튼 결과
            var gender = binding.rgGender.checkedRadioButtonId
            //지역 스피너 결과
            var place = binding.spPlace.selectedItem.toString()
            //장르 라디오버튼 결과
            var genre = binding.rgGenre.checkedRadioButtonId
            //이용요금 라디오버튼 결과
            var fee = binding.rgFee.checkedRadioButtonId

            println("$age, $gender, $place, $genre, $fee")

            // 하나라도 체크하지 않았으면 체크해달라고 토스트 메시지 띄우기
            if(age==-1 || gender==-1 || genre ==-1 || fee == -1)
                Toast.makeText(this, "모든 항목을 체크해주세요", Toast.LENGTH_SHORT ).show()
            else{
                //모든 항목에 체크 완료됐으면 결과 페이지 띄우기
                var resultIntent = Intent(this, ResultActivity::class.java)
                resultIntent.putExtra("age", age)
                resultIntent.putExtra("gender", gender)
                resultIntent.putExtra("place", place)
                resultIntent.putExtra("genre",genre)
                resultIntent.putExtra("fee", fee)
                startActivity(resultIntent)
                finish()
            }
        }

    }


}
