package com.hyunjung.munuhmunhwa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hyunjung.munuhmunhwa.databinding.ItemContentsBinding

/**
* 재활용한 뷰에 데이터 바꿔주는 작업
 */
class ContentAdapter(var contentItems: ArrayList<ContentItem>) : RecyclerView.Adapter<ContentAdapter.ContentViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.item_contents, parent, false)
        return ContentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contentItems?.size?:0
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.binding?.tvContentTitle?.text = contentItems[position].title
        holder.binding?.tvContentSubTitle?.text = contentItems[position].subtitle

        Glide
            .with(App.instance)
            .load(contentItems[position].img)
                //.centerCrop()
            .placeholder(R.drawable.kakao_talk_photo_2020_08_16_17_09_19)
            .into(holder.binding?.imgContent!!)
    }

    class ContentViewHolder(view: View): RecyclerView.ViewHolder(view){
        var binding = DataBindingUtil.bind<ItemContentsBinding>(view)
    }
}