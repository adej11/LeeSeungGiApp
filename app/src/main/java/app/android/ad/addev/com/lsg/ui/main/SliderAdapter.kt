package app.android.ad.addev.com.lsg.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import app.android.ad.addev.com.core.domain.model.SliderItem
import app.android.ad.addev.com.core.utils.Constant.Companion.URL_LSG
import app.android.ad.addev.com.lsg.R
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter :
    SliderViewAdapter<SliderAdapter.SliderAdapterHolder>() {
    private var mSliderItems: MutableList<SliderItem> = ArrayList()
    fun renewItems(sliderItems: MutableList<SliderItem>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterHolder {
        val inflate: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_slider, parent, false)
        return SliderAdapterHolder(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterHolder, position: Int) {
        val sliderItem: SliderItem = mSliderItems[position]
        Glide.with(viewHolder.itemView)
            .load(URL_LSG + sliderItem.image)
            .fitCenter()
            .into(viewHolder.imageViewBackground)
        viewHolder.itemView.setOnClickListener {

        }
    }

    override fun getCount(): Int {
        return mSliderItems.size
    }

    inner class SliderAdapterHolder(itemView: View) : ViewHolder(itemView) {
        var imageViewBackground: ImageView = itemView.findViewById(R.id.image)
    }
}