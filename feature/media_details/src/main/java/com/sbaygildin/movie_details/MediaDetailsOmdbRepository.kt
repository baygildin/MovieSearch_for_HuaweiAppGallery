package com.sbaygildin.movie_details

import androidx.lifecycle.ViewModel
import com.sbaygildin.search.OmdbApi
import com.sbaygildin.search.model.SearchResponseById
import javax.inject.Inject

class MediaDetailsOmdbRepository @Inject constructor(
    private val omdbApi: OmdbApi,
) : ViewModel() {
    suspend fun getMediaInfoById(mediaId: String)
            : SearchResponseById {
        return omdbApi.searchById(mediaId)
    }
}