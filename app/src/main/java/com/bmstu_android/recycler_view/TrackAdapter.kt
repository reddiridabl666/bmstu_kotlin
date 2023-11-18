package com.bmstu_android.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bmstu_android.recycler_view.databinding.TrackItemBinding
import com.squareup.picasso.Picasso

data class Track(val title: String, val duration: String)

class TrackAdapter(private val dataSet: Album) :  RecyclerView.Adapter<TrackAdapter.ViewHolder>() {
    class ViewHolder(private var binding: TrackItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setContents(position: Int, url: String, track: Track) {
            binding.trackTitle.text = "${position+1} ${track.title} ${track.duration}"
            Picasso.get().load(url).into(binding.cover)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            TrackItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.setContents(position, dataSet.url, dataSet.tracks[position])
    }

    override fun getItemCount() = dataSet.tracks.size
}