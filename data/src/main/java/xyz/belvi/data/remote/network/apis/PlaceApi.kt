package xyz.belvi.data.remote.network.apis

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import xyz.belvi.data.remote.model.response.NetworkResponses

interface PlaceApi {

//    https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=6.5243793,3.3792057&radius=1500&type=atm=atm&key=AIzaSyAF1Hmr3GSmU_V5klToICkzXU6Yun5y_2

    @GET("/maps/api/place/nearbysearch/json")
    fun nearbyPlaces(
        @Query("location") location: String,
        @Query("radius") radius: Long,
        @Query("type") type: String,
        @Query("key") key: String
    ): Observable<NetworkResponses.SearchResultResponse>


}

