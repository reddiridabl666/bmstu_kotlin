package com.bmstu_android.recycler_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bmstu_android.recycler_view.databinding.AlbumItemBinding
import com.google.gson.Gson
import com.squareup.picasso.Picasso

data class Album(val title: String, val band: String, val url: String, val tracks: List<Track>)

class AlbumAdapter(private val dataSet: List<Album>) :  RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
    class ViewHolder(private var binding: AlbumItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var data: Album

        fun setContents(album: Album) {
            binding.album.text = album.title
            binding.band.text = album.band
            data = album
            Picasso.get().load(album.url).into(binding.image)
        }

        init {
            binding.root.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(ALBUM, Gson().toJson(data))
                Navigation.createNavigateOnClickListener(R.id.action_albumsFragment_to_trackListFragment, bundle).onClick(binding.root)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            AlbumItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.setContents(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}
