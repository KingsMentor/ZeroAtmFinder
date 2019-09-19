package xyz.belvi.domain.impl

import io.reactivex.Observable
import xyz.belvi.data.remote.repository.SearchRepo
import xyz.belvi.domain.states.DataStates
import xyz.belvi.domain.states.resultSuccessful
import xyz.belvi.domain.states.searchFailed
import xyz.belvi.domain.usecases.SearchUsecase

open class SearchImpl(val searchRepo: SearchRepo = SearchRepo()) : SearchUsecase {

    private val RADIUS = 50L
    private val QUERY_TYPE = "ATM"
    private val API_KEY = "AIzaSyAF1Hmr3GSmU_V5klToICkzXU6Yun5y_2Y"
    override fun nearbyATM(lat: Double, lng: Double): Observable<DataStates> {
        return searchRepo.searchAtms("$lat,$lng", QUERY_TYPE, API_KEY,RADIUS)
            .map { response ->
                response.results?.let {
                    if (response.status == "OK")
                        resultSuccessful(it)
                    else
                        searchFailed(response.error_message)
                } ?: kotlin.run {
                    searchFailed(response.error_message)
                }
            }
    }

}