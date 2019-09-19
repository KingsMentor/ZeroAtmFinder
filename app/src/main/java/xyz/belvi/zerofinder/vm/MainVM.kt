package xyz.belvi.zerofinder.vm

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import xyz.belvi.domain.states.DataStates
import xyz.belvi.domain.usecases.SearchUsecase
import xyz.belvi.zerofinder.exts.standard

open class MainVM(private val searchUsecase: SearchUsecase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val searchResults = MediatorLiveData<DataStates>()

    fun nearByATM(lat: Double, lng: Double) {
        compositeDisposable.addAll(
            searchUsecase.nearbyATM(lat, lng).standard(searchResults)
                .subscribe {
                    searchResults.postValue(it)
                }
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}