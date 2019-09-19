package xyz.belvi.zerofinder.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import xyz.belvi.domain.impl.SearchImpl

object MainVMFactory :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainVM(SearchImpl()) as T
    }
}