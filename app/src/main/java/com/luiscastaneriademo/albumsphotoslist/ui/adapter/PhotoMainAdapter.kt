package com.luiscastaneriademo.albumsphotoslist.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luiscastaneriademo.albumsphotoslist.R
import com.luiscastaneriademo.albumsphotoslist.databinding.PhotoListItemBinding
import com.luiscastaneriademo.albumsphotoslist.data.model.PhotoModelItem
import com.squareup.picasso.Picasso


class PhotoMainAdapter(
    private val context: Context,
    val onClick: Click,
    private val photoList: List<PhotoModelItem>) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface Click {
        fun onImageClick(url: String, thumbnailUrl: String, title: String)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.photo_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(photoList[position], position)
        }
    }

    inner class MainViewHolder(itemview: View) : BaseViewHolder<PhotoModelItem>(itemview) {
        private val binding = PhotoListItemBinding.bind(itemview)

        override fun bind(item: PhotoModelItem, position: Int) {
            Picasso.get()
                .load(item.thumbnailUrl)
                .error(R.drawable.ic_launcher_background)
                .into(binding.imCoverPhoto)

            binding.tvNamePhoto.text = item.title
            binding.cardView.setOnClickListener {
                onClick.onImageClick(item.url, item.thumbnailUrl, item.title)
            }

        }


    }
}