package com.luiscastaneriademo.albumsphotoslist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.luiscastaneriademo.albumsphotoslist.R
import com.luiscastaneriademo.albumsphotoslist.databinding.FragmentIndexBinding
import com.luiscastaneriademo.albumsphotoslist.databinding.FragmentPhotoDetailBinding


class IndexFragment : Fragment() {

    private var _binding: FragmentIndexBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIndexBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgAlbumList.setOnClickListener {
            findNavController().navigate(
                R.id.action_indexFragment_to_albumsListFragment)

        }
        binding.imgPhotoList.setOnClickListener {
            findNavController().navigate(
                R.id.action_indexFragment_to_photoListFragment)

        }
    }

}