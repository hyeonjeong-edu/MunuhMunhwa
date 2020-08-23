package com.hyunjung.munuhmunhwa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

class IntroActivity : AppCompatActivity() {

    val DURATION:Long = 2500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        var bg:ImageView =  findViewById(R.id.imageView)
        val translateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_anim)

        bg.startAnimation(translateAnimation)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }, DURATION)
    }

    override fun onBackPressed() {

    }
}
