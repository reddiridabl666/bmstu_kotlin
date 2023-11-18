package com.bmstu_android.recycler_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bmstu_android.recycler_view.databinding.FragmentAlbumsBinding
import com.google.gson.GsonBuilder

private const val ALBUMS_PARAM = "albums"

class AlbumsFragment : Fragment() {
    private lateinit var albums: List<Album>
    private lateinit var binding: FragmentAlbumsBinding
    private var albumsPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            albumsPath = it.getString(ALBUMS_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumsBinding.inflate(inflater, container, false)

        val resultString = albumsPath?.let {
            context?.assets
                ?.open(it)
                ?.bufferedReader()
                .use { it?.readText() ?: "[]"  }
        }

        val gson = GsonBuilder().create()
        albums =  gson.fromJson(resultString, Array<Album>::class.java).toList()

        val rv = binding.recycler
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = AlbumAdapter(albums)

        return binding.root
    }
}