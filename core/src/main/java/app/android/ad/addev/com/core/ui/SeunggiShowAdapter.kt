package app.android.ad.addev.com.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.android.ad.addev.com.core.R
import app.android.ad.addev.com.core.domain.model.SeunggiShow
import app.android.ad.addev.com.core.utils.Constant
import com.bumptech.glide.Glide
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
        Glide.with(holder.itemView)
            .load(Constant.URL_IMG_ADAPTER + data.poster_path)
            .into(holder.itemView.iv_item_image)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(listData[position])
        }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}