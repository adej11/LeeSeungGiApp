package app.android.ad.addev.com.favorite.favorite

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import app.android.ad.addev.com.core.ui.SeunggiShowAdapter
import app.android.ad.addev.com.core.utils.Constant
import app.android.ad.addev.com.favorite.R
import app.android.ad.addev.com.favorite.di.DaggerDfmComponent
import app.android.ad.addev.com.lsg.di.DfmDependencies
import app.android.ad.addev.com.lsg.ui.detail.DetailShowActivity
import dagger.hilt.android.EntryPointAccessors
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        DaggerDfmComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    DfmDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(Color.WHITE)

        val seunggiAdapter = SeunggiShowAdapter()
        seunggiAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailShowActivity::class.java)
            intent.putExtra(Constant.SHOW_ID, selectedData)
            startActivity(intent)
        }
        favoriteViewModel.seunggiShow.observe(this, { favoriteShow ->
            if (favoriteShow != null) {
                seunggiAdapter.setData(favoriteShow)
                progress_bar_playing.visibility =
                    if (favoriteShow.isNotEmpty()) View.GONE else View.VISIBLE
            }
        })

        with(rv_movie) {
            val staggeredGridLayoutManager =
                StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
            rv_movie.layoutManager = staggeredGridLayoutManager
            setHasFixedSize(true)
            adapter = seunggiAdapter
        }

        supportActionBar?.title = Constant.MY_FAVORITE
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        window.statusBarColor = Color.TRANSPARENT
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