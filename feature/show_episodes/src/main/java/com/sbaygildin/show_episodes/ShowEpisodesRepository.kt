package com.sbaygildin.show_episodes

import com.sbaygildin.search.OmdbApi
import com.sbaygildin.search.model.SearchResponseBySeason
import javax.inject.Inject

class ShowEpisodesRepository @Inject constructor(
    private val omdbApi: OmdbApi
) {
    suspend fun getEpisodesByTitleAndSeasonNumber(
        title: String,
        seasonNumber: String
    ): SearchResponseBySeason {
        return omdbApi.searchBySeason(title, seasonNumber.toString())
    }
}
