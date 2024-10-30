package com.sbaygildin.show_episodes

import com.sbaygildin.search.OmdbApi
import com.sbaygildin.search.model.SearchResponseEpisodeFullInfo
import javax.inject.Inject

class ShowEpisodeRepository @Inject constructor(
    private val OmbdiApi: OmdbApi
) {
    suspend fun getEpisodeByTitleAndSeasonAndEpisodeNumber(
        title: String,
        seasonNumber: String,
        episodeNumber: String
    ): SearchResponseEpisodeFullInfo {
        return OmbdiApi.searchByEpisode(title, seasonNumber, episodeNumber)
    }
}