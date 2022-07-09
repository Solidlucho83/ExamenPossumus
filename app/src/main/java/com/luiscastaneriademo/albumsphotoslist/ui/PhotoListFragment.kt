package com.luiscastaneriademo.albumsphotoslist.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.luiscastaneriademo.albumsphotoslist.R
import com.luiscastaneriademo.albumsphotoslist.core.Resource
import com.luiscastaneriademo.albumsphotoslist.core.VMFactory
import com.luiscastaneriademo.albumsphotoslist.data.LocalDataSource
import com.luiscastaneriademo.albumsphotoslist.data.RemoteDataSource
import com.luiscastaneriademo.albumsphotoslist.data.RepoImplement
import com.luiscastaneriademo.albumsphotoslist.data.database.PhotoDataBase
import com.luiscastaneriademo.albumsphotoslist.databinding.FragmentPhotoListBinding
import com.luiscastaneriademo.albumsphotoslist.ui.adapter.PhotoMainAdapter
import com.luiscastaneriademo.albumsphotoslist.ui.viewmodel.MainViewModel


class PhotoListFragment : Fragment(), PhotoMainAdapter.Click {

    private var _binding: FragmentPhotoListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel> {
        VMFactory(
            RepoImplement(
                RemoteDataSource(),
                LocalDataSource(PhotoDataBase.getDatabase(requireContext()).getPhotoList())
            )
        )
    }
    private lateinit var adapter: PhotoMainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       binding.btnreturn.setOnClickListener {
           findNavController().navigate(
               R.id.action_photoListFragment_to_indexFragment
           )
       }
        initRw()
        viewModel.fechtListPhotos().observe(viewLifecycleOwner, Observer { result ->

            when (result) {
                is Resource.Loading -> {
                    binding.progressBar3.visibility = View.VISIBLE
                    Log.d("Loading", "${result}")
                }
                is Resource.Success -> {
                    binding.rvPhotoList.adapter = PhotoMainAdapter(
                        requireContext(), this@PhotoListFragment,
                        result.data.result
                    )
                    binding.progressBar3.visibility = View.GONE
                    Log.d("Success", "${result.data.result}")
                }
                is Resource.Failure -> {
                    binding.progressBar3.visibility = View.GONE
                    Log.d("Error", "${result.exception}")
                }
            }
        })


    }

    private fun initRw() {
        binding.rvPhotoList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPhotoList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )


    }

    override fun onImageClick(url: String, thumbnailUrl: String, title: String) {
        findNavController().navigate(
            R.id.action_photoListFragment_to_photoDetailFragment,
            bundleOf("cover" to url, "title" to title, "thumbnailUrl" to thumbnailUrl)
        )
    }

}

