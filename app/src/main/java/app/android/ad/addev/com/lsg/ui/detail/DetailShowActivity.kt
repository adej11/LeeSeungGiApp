package app.android.ad.addev.com.lsg.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import app.android.ad.addev.com.core.data.Resource
import app.android.ad.addev.com.core.domain.model.SeunggiShow
import app.android.ad.addev.com.core.ui.CastShowAdapter
import app.android.ad.addev.com.core.utils.Constant.Companion.SHOW_ID
import app.android.ad.addev.com.core.utils.Constant.Companion.URL_IMG_DETAIL
import app.android.ad.addev.com.lsg.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import timber.log.Timber

@AndroidEntryPoint
class DetailShowActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModels()
    private val castShowAdapter = CastShowAdapter()
    private var menuItem: Menu? = null
    private var seunggiShow: SeunggiShow? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        seunggiShow = intent.getParcelableExtra(SHOW_ID)
        val id = seunggiShow?.id

        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(Color.WHITE)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        seunggiShow?.let { setData(it) }
        id?.let { seunggiShow?.media_type?.let { it1 -> detailViewModel.init(it, it1) } }
        detailViewModel.cast?.observe(this, { castsList ->
            if (castsList != null) {
                when (castsList) {
                    is Resource.Loading -> progress_bar_playing.visibility = View.VISIBLE
                    is Resource.Success -> {
                        progress_bar_playing.visibility = View.GONE
                        castShowAdapter.setData(castsList.data)
                        setDataCast()
                    }
                    is Resource.Error -> {
                        progress_bar_playing.visibility = View.GONE
                        Timber.tag("lsg").d(castsList.message)
                    }
                }
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        isFavorite = seunggiShow!!.isFavorite
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                true
            }
            R.id.add_to_favorite -> {
                isFavorite = !isFavorite
                seunggiShow?.let { detailViewModel.setFavoriteShow(it, isFavorite) }
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun setData(data: SeunggiShow) {
        text_description.text = data.overview
        supportActionBar?.title = if (data.name.contains("null")) data.title else data.name
        text_date?.text = String.format(
            "Release Date %s",
            if (data.release_date.contains("null")) data.first_air_date else data.release_date
        )
        rating.rating = data.vote_average.toFloat() / 2

        Glide.with(this)
            .load(URL_IMG_DETAIL + data.poster_path)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(image_poster)
    }

    private fun setDataCast() {
        with(rv_cast) {
            rv_cast.isNestedScrollingEnabled = false
            rv_cast.layoutManager = LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            rv_cast.setHasFixedSize(true)
            val staggeredGridLayoutManager =
                StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
            rv_cast.layoutManager = staggeredGridLayoutManager
            adapter = castShowAdapter
            rv_cast.adapter = adapter
        }
    }

}