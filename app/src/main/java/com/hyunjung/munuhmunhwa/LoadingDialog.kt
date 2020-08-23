package com.hyunjung.munuhmunhwa

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.bumptech.glide.Glide

class LoadingDialog constructor(context: Context): Dialog(context){
    val DURATION:Long = 3500

    override fun onCreate(savedInstanceState: Bundle?) {
        setCanceledOnTouchOutside(false)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setContentView(R.layout.dialog_loading)

        Handler().postDelayed({
        }, DURATION)

    }
}
