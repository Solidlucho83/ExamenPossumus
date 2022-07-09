package com.luiscastaneriademo.albumsphotoslist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.luiscastaneriademo.albumsphotoslist.R
import com.luiscastaneriademo.albumsphotoslist.databinding.FragmentPhotoDetailBinding
import com.squareup.picasso.Picasso


class PhotoDetailFragment : Fragment(R.layout.fragment_photo_detail) {
    private var _binding: FragmentPhotoDetailBinding? = null
    private val binding get() = _binding!!
    private var cover: String? = ""
    private var title: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cover = it.getString(COVER)
            title = it.getString(TITLE)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get()
            .load(cover)
            .error(R.drawable.ic_launcher_background)
            .into(binding.imgDetail)
        binding.tvTitleDetail.text = title
        binding.imBack.setOnClickListener {
            findNavController().navigate(
                R.id.action_photoDetailFragment_to_photoListFragment)

        }
    }

    companion object {
        private const val COVER = "cover"
        private const val TITLE = "title"


    }


}