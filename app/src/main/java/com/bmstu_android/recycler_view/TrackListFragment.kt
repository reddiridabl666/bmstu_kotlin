package com.bmstu_android.recycler_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bmstu_android.recycler_view.databinding.FragmentTrackListBinding
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso

const val ALBUM = "album"

class TrackListFragment : Fragment() {
    private lateinit var album: Album
    private lateinit var binding: FragmentTrackListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val resultString = it.getString(ALBUM)
            val gson = GsonBuilder().create()
            album = gson.fromJson(resultString, Album::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrackListBinding.inflate(inflater, container, false)

        Picasso.get().load(album.url).into(binding.image)
        binding.band.text = album.band
        binding.album.text = album.title

        val rv = binding.tracksRecycler
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = TrackAdapter(album)

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_trackListFragment_to_albumsFragment)
        }

        return binding.root
    }
}