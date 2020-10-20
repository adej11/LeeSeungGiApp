package app.android.ad.addev.com.lsg.ui.album

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import app.android.ad.addev.com.core.data.Resource
import app.android.ad.addev.com.core.domain.model.Album
import app.android.ad.addev.com.core.ui.TrackAdapter
import app.android.ad.addev.com.core.utils.Constant.Companion.ALBUM
import app.android.ad.addev.com.core.utils.Constant.Companion.SHOW_ID
import app.android.ad.addev.com.lsg.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail_album.*
import timber.log.Timber

@AndroidEntryPoint
class DetailAlbumActivity : AppCompatActivity() {
    private val albumViewModel: AlbumViewModel by viewModels()
    private val trackAdapter = TrackAdapter()
    private var album: Album? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail_album)
        album = intent.getParcelableExtra(SHOW_ID)
        val id = album?.id

        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(Color.WHITE)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = ALBUM

        Glide.with(applicationContext)
            .load(album?.image)
            .apply(RequestOptions().override(350, 350))
            .into(image_album)
        text_date.text = album?.releaseDate
        text_title.text = album?.name

        id?.let { albumViewModel.init(it) }
        albumViewModel.tracks?.observe(this, { tracks ->
            if (tracks != null) {
                when (tracks) {
                    is Resource.Loading -> progress_bar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        progress_bar.visibility = View.GONE
                        trackAdapter.setData(tracks.data)
                    }
                    is Resource.Error -> {
                        progress_bar.visibility = View.GONE
                        Timber.tag("lsg").d(tracks.message)
                    }
                }
            }
        })

        with(rv_track) {
            rv_track.layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = trackAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}