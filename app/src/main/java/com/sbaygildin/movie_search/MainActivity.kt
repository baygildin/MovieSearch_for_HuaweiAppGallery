package com.sbaygildin.movie_search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import com.sbaygildin.movie_search.R
import com.sbaygildin.core.MenuItemClickListener
import com.sbaygildin.media_details.MediaDetailsFragmentDirections
import com.sbaygildin.poster.PosterFragmentDirections
import com.sbaygildin.search.SearchFragmentDirections
import com.sbaygildin.show_episodes.ShowEpisodesFragmentDirections
import com.sbaygildin.show_episodes.ShowSeasonsFragmentDirections
import com.sbaygildin.show_episodes.ShowEpisodeFragmentDirections
import com.sbaygildin.liked.LikedFragmentDirections


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), com.sbaygildin.navigation.Navigator, MenuItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)

    }

    override fun navigateSearchToMediaDetailsWithId(id: String) {
        val action = SearchFragmentDirections.navigateSearchToMediaDetailsWithId(id)
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun navigatePosterToMediaDetailsWithId(id: String){
        val action = PosterFragmentDirections.navigatePosterToMediaDetailsWithId(id)
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun navigateMediaDetailsToSearchWithId(id: String){
        val action = MediaDetailsFragmentDirections.navigateMediaDetailsToSearchWithId(id)
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun navigateMediaDetailsToPosterWithId(id: String){
        val action = MediaDetailsFragmentDirections.navigateMediaDetailsToPosterWithId(id)
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun navigateMediaDetailsToShowSeasonsWithId(id: String){
        val action = MediaDetailsFragmentDirections.navigateMediaDetailsToShowSeasonsWithId(id)
        findNavController(R.id.nav_host_fragment).navigate(action)
    }
    override fun navigateShowEpisodesToShowEpisode(title: String, seasonNumber: String, episodeNumber: String) {
        val action =
            ShowEpisodesFragmentDirections.navigateShowEpisodesToShowEpisode(title, seasonNumber, episodeNumber)
        findNavController(R.id.nav_host_fragment).navigate(action)
    }
    override fun navigateShowSeasonsToShowEpisodes(title: String, seasonNumber: String) {
        val action =
            ShowSeasonsFragmentDirections.navigateShowSeasonsToShowEpisodes(title, seasonNumber)
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

     override fun navigateShowSeasonsToLiked() {
        val action =
            ShowSeasonsFragmentDirections.navigateShowSeasonsToLiked()
        findNavController(R.id.nav_host_fragment).navigate(action)

    }

    override fun navigateShowEpisodeToLiked() {
        val action =
            ShowEpisodeFragmentDirections.navigateShowEpisodeToLiked()
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun navigateShowEpisodesToLiked() {
        val action =
            ShowEpisodesFragmentDirections.navigateShowEpisodesToLiked()
        findNavController(R.id.nav_host_fragment).navigate(action)
    }
    override fun navigateMediaDetailsToLiked(){
        val action =
            MediaDetailsFragmentDirections.navigateMediaDetailsToLiked()
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun navigateLikedToMediaDetailsWithId(id: String) {
        val action =
            LikedFragmentDirections.navigateLikedToMediaDetailsWithId(id)
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun onMenuItemClicked(itemId: Int) {
        when (itemId) {
            R.id.action_favorites -> {
                val currentFragment = supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.fragments?.get(0)
                val fragmentName = currentFragment?.javaClass?.simpleName
                Log.d("MainActivity", "Favorites menu item clicked from fragment: $fragmentName")
                when (fragmentName) {
                    "MediaDetailsFragment" -> navigateMediaDetailsToLiked()
                    "ShowEpisodeFragment" -> navigateShowEpisodeToLiked()
                    "ShowEpisodesFragment" -> navigateShowEpisodesToLiked()
                    "ShowSeasonsFragment" -> navigateShowSeasonsToLiked()
                }
            }
        }
    }

}
