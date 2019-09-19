package xyz.belvi.domain.usecases

import io.reactivex.Observable
import xyz.belvi.domain.states.DataStates

interface SearchUsecase {
    fun nearbyATM(lat: Double, lng: Double) : Observable<DataStates>
}