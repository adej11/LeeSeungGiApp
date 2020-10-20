package app.android.ad.addev.com.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.android.ad.addev.com.core.R
import app.android.ad.addev.com.core.domain.model.Track
import kotlinx.android.synthetic.main.items_track.view.*
import java.util.*

class TrackAdapter : RecyclerView.Adapter<TrackAdapter.ListViewHolder>() {
    private var listData = ArrayList<Track>()
    private var onItemClick: ((Track) -> Unit)? = null

    fun setData(newListData: List<Track>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items_track, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.itemView.tv_song.text = data.song
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(listData[position])
        }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}