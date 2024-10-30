package com.sbaygildin.show_episodes

import com.sbaygildin.search.OmdbApi
import com.sbaygildin.search.model.SearchResponseBySeason
import javax.inject.Inject
class ShowSeasonsRepository @Inject constructor(
    private val ombdiApi: OmdbApi
){
    suspend fun getSeasonsByIdAndSeasons(id: String) : SearchResponseBySeason {
        return ombdiApi.searchSeasonByIdAndSeason(id, "1")
    }
}