package xyz.belvi.zerofinder.exts

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import xyz.belvi.domain.states.DataResponseError
import xyz.belvi.domain.states.DataStates


fun <T> io.reactivex.Observable<T>.standard(liveObj: MutableLiveData<DataStates>? = null): Observable<T> {

    return this
        .doOnError {
            liveObj?.postValue(DataResponseError(it))
        }
        .onErrorResumeNext(io.reactivex.Observable.empty())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


}
