package app.android.ad.addev.com.lsg.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import app.android.ad.addev.com.core.data.Resource
import app.android.ad.addev.com.lsg.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.app_bar_main.*
import timber.log.Timber


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var adapter: SliderAdapter? = null
    private lateinit var sliderView: SliderView
    private val bannerViewModel: BannerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sliderView = findViewById(R.id.image_slider)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(Color.WHITE)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        window.statusBarColor = Color.TRANSPARENT
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_performance, R.id.navigation_album, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        adapter = SliderAdapter()
        sliderView.setSliderAdapter(adapter!!)
        sliderView.setIndicatorAnimation(IndicatorAnimationType.SLIDE)
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        sliderView.indicatorSelectedColor = Color.WHITE
        sliderView.indicatorUnselectedColor = Color.GRAY
        sliderView.scrollTimeInSec = 3
        sliderView.isAutoCycle = true
        sliderView.startAutoCycle()

        bannerViewModel.banners.observe(this, { bannerList ->
            if (bannerList != null) {
                when (bannerList) {
                    is Resource.Loading -> progress_bar_playing.visibility = View.VISIBLE
                    is Resource.Success -> {
                        progress_bar_playing.visibility = View.GONE
                        bannerList.data?.toMutableList()?.let { adapter!!.renewItems(it) }
                    }
                    is Resource.Error -> {
                        progress_bar_playing.visibility = View.GONE
                        Timber.tag("lsg").d(bannerList.message)
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    override fun onStop() {
        sliderView.stopAutoCycle()
        super.onStop()
    }
}