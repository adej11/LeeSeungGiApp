package app.android.ad.addev.com.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.android.ad.addev.com.core.R
import app.android.ad.addev.com.core.domain.model.SeunggiShow
import app.android.ad.addev.com.core.utils.Constant.Companion.URL_IMG_ADAPTER
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.items_list_show.view.*
import java.util.*

class SeunggiShowAdapter : RecyclerView.Adapter<SeunggiShowAdapter.ListViewHolder>() {

    private var listData = ArrayList<SeunggiShow>()
    var onItemClick: ((SeunggiShow) -> Unit)? = null

    fun setData(newListData: List<SeunggiShow>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_list_show, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: SeunggiShow) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(URL_IMG_ADAPTER + data.poster_path)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(iv_item_image)
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}