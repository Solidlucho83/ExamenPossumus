package com.luiscastaneriademo.albumsphotoslist.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.luiscastaneriademo.albumsphotoslist.R
import com.luiscastaneriademo.albumsphotoslist.core.Resource
import com.luiscastaneriademo.albumsphotoslist.ui.adapter.AlbumMainAdapter
import com.luiscastaneriademo.albumsphotoslist.core.VMFactory
import com.luiscastaneriademo.albumsphotoslist.data.LocalDataSource
import com.luiscastaneriademo.albumsphotoslist.data.RemoteDataSource
import com.luiscastaneriademo.albumsphotoslist.data.RepoImplement
import com.luiscastaneriademo.albumsphotoslist.data.database.PhotoDataBase
import com.luiscastaneriademo.albumsphotoslist.databinding.FragmentAlbumsListBinding
import com.luiscastaneriademo.albumsphotoslist.ui.viewmodel.MainViewModel


class AlbumsListFragment : Fragment() {

    private var _binding: FragmentAlbumsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel> { VMFactory(RepoImplement(RemoteDataSource(),
        LocalDataSource(PhotoDataBase.getDatabase(requireContext()).getPhotoList())
    )) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlbumsListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnreturnAlbum.setOnClickListener {
            findNavController().navigate(
                R.id.action_albumsListFragment_to_indexFragment
            )
        }
        initRw()
        viewModel.fecthListAlbums.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Log.d("Loading", "${result}")
                }
                is Resource.Success -> {
                    binding.rvAlbumsList.adapter = AlbumMainAdapter(requireContext(), result.data)
                    binding.progressBar.visibility = View.GONE
                }
                is Resource.Failure -> {
                    Log.d("Error", "${result.exception}")
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun initRw() {
        binding.rvAlbumsList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAlbumsList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )


    }
}