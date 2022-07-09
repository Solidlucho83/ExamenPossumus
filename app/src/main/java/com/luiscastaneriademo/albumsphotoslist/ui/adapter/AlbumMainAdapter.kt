package com.luiscastaneriademo.albumsphotoslist.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luiscastaneriademo.albumsphotoslist.R
import com.luiscastaneriademo.albumsphotoslist.databinding.AlbumListBinding
import com.luiscastaneriademo.albumsphotoslist.data.model.AlbumModelItem


class AlbumMainAdapter(
    private val context: Context,
    private val albumList: List<AlbumModelItem>
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.album_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(albumList[position], position)
        }
    }

    inner class MainViewHolder(itemview: View) : BaseViewHolder<AlbumModelItem>(itemview) {
        private val binding = AlbumListBinding.bind(itemview)

        override fun bind(item: AlbumModelItem, position: Int) {
            binding.tvTitleAlbum.text = item.title

        }


    }
}