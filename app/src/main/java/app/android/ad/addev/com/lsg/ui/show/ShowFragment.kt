package app.android.ad.addev.com.lsg.ui.show

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import app.android.ad.addev.com.core.data.Resource
import app.android.ad.addev.com.core.ui.SeunggiShowAdapter
import app.android.ad.addev.com.core.utils.Constant.Companion.SHOW_ID
import app.android.ad.addev.com.lsg.R
import app.android.ad.addev.com.lsg.ui.detail.DetailShowActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber

@AndroidEntryPoint
class ShowFragment : Fragment() {
    private val showViewModel: ShowViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        if (activity != null) {
            val seunggiAdapter = SeunggiShowAdapter()
            seunggiAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailShowActivity::class.java)
                intent.putExtra(SHOW_ID, selectedData)
                startActivity(intent)
            }

            showViewModel.seunggiShow.observe(viewLifecycleOwner, { seunggiShow ->
                if (seunggiShow != null) {
                    when (seunggiShow) {
                        is Resource.Loading -> progress_bar_playing.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progress_bar_playing.visibility = View.GONE
                            seunggiAdapter.setData(seunggiShow.data)
                        }
                        is Resource.Error -> {
                            progress_bar_playing.visibility = View.GONE
                            Timber.tag("lsg").d(seunggiShow.message)
                        }
                    }
                }
            })

            with(rv_movie) {
                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
                rv_movie.layoutManager = staggeredGridLayoutManager
                setHasFixedSize(true)
                adapter = seunggiAdapter
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_to_favorite -> {
                val uri = Uri.parse("addev://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        return false
    }
}