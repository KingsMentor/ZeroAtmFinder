package xyz.belvi.data.remote.repository

import android.util.Log
import io.reactivex.Observable
import xyz.belvi.data.remote.model.response.NetworkResponses
import xyz.belvi.data.remote.network.ApiClient
import xyz.belvi.data.remote.network.apis.PlaceApi

open class SearchRepo(val api: PlaceApi = ApiClient.retrofit.create(PlaceApi::class.java)) {

    fun searchAtms(
        location: String,
        type: String,
        key: String,
        radius: Long
    ): Observable<NetworkResponses.SearchResultResponse> {
        return api.nearbyPlaces(location, radius, type, key)
    }
}