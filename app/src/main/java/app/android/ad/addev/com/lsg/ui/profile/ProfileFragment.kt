package app.android.ad.addev.com.lsg.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import app.android.ad.addev.com.core.data.Resource
import app.android.ad.addev.com.core.domain.model.Profile
import app.android.ad.addev.com.lsg.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*
import timber.log.Timber

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private val profileViewModel: ProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        profileViewModel.profile.observe(viewLifecycleOwner, { profile ->
            if (profile != null) {
                when (profile) {
                    is Resource.Loading -> progress_bar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        progress_bar.visibility = View.GONE
                        profile.data?.get(0)?.let { setData(it) }
                    }
                    is Resource.Error -> {
                        progress_bar.visibility = View.GONE
                        Timber.tag("lsg").d(profile.message)
                    }
                }
            }
        })

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
    private fun setData(data: Profile) {
        textName.text = "Name\t:" + data.name
        textHangul.text = "Hangul\t:" + data.hangul
        textDate.text = "Birthdate\t:" + data.birthdate
        textHeight.text = "Height\t:" + data.height
        textInstagram.text = "Instagram\t:" + data.instagram
        textWebsite.text = "Website :" + data.website
        text_description.text = data.biography
    }
}