package app.android.ad.addev.com.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.android.ad.addev.com.core.R
import app.android.ad.addev.com.core.domain.model.Cast
import app.android.ad.addev.com.core.utils.Constant.Companion.URL_IMG_ADAPTER
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.items_cast.view.*
import java.util.*

class CastShowAdapter : RecyclerView.Adapter<CastShowAdapter.ListViewHolder>() {

    private var listData = ArrayList<Cast>()
    private var onItemClick: ((Cast) -> Unit)? = null

    fun setData(newListData: List<Cast>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_cast, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        Glide.with(holder.itemView)
            .load(URL_IMG_ADAPTER + data.profile_path)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(holder.itemView.iv_item_image)
        holder.itemView.tv_character.text = data.name
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(listData[position])
        }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}