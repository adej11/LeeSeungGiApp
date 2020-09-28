package app.android.ad.addev.com.lsg.ui.album

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
import app.android.ad.addev.com.core.ui.AlbumAdapter
import app.android.ad.addev.com.core.utils.Constant
import app.android.ad.addev.com.lsg.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class AlbumFragment : Fragment() {
    private val albumViewModel: AlbumViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            setHasOptionsMenu(true)
            val albumAdapter = AlbumAdapter()
            albumAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailAlbumActivity::class.java)
                intent.putExtra(Constant.SHOW_ID, selectedData)
                startActivity(intent)
            }

            albumViewModel.albums.observe(viewLifecycleOwner, { album ->
                if (album != null) {
                    when (album) {
                        is Resource.Loading -> progress_bar_playing.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progress_bar_playing.visibility = View.GONE
                            albumAdapter.setData(album.data)
                        }
                        is Resource.Error -> {
                            progress_bar_playing.visibility = View.GONE
                        }
                    }
                }
            })

            with(rv_movie) {
                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
                rv_movie.layoutManager = staggeredGridLayoutManager
                setHasFixedSize(true)
                adapter = albumAdapter
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
