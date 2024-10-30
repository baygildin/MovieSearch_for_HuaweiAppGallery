package com.sbaygildin.navigation

interface Navigator {
    fun navigateSearchToMediaDetailsWithId(id: String)
    fun navigatePosterToMediaDetailsWithId(id: String)
    fun navigateMediaDetailsToSearchWithId(id: String)
    fun navigateMediaDetailsToPosterWithId(id: String)
    fun navigateMediaDetailsToShowSeasonsWithId(id: String)
    fun navigateShowEpisodesToShowEpisode(title: String, seasonNumber: String, episodeNumber: String)
    fun navigateShowSeasonsToShowEpisodes(title: String, seasonNumber: String)

    fun navigateShowSeasonsToLiked()
    fun navigateMediaDetailsToLiked()
    fun navigateShowEpisodeToLiked()
    fun navigateShowEpisodesToLiked()
    fun navigateLikedToMediaDetailsWithId(id: String)





}
