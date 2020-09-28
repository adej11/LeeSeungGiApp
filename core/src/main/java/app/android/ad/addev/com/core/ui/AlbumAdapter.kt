package app.android.ad.addev.com.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.android.ad.addev.com.core.R
import app.android.ad.addev.com.core.domain.model.Album
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.items_list_album.view.*
import kotlinx.android.synthetic.main.items_list_show.view.iv_item_image
import java.util.*

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.ListViewHolder>() {
    private var listData = ArrayList<Album>()
    var onItemClick: ((Album) -> Unit)? = null

    fun setData(newListData: List<Album>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_list_album, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Album) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(iv_item_image)
                itemView.tv_item_title.text=data.name
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}