package xyz.belvi.domain.states

import xyz.belvi.data.remote.model.retroObj.ResponseData


data class resultSuccessful(var results: MutableList<ResponseData.SearchResult>) : SearchStates()
data class searchFailed(var message: String) : SearchStates()


