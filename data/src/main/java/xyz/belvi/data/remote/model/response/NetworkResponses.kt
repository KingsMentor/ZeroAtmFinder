package xyz.belvi.data.remote.model.response

import xyz.belvi.data.remote.model.retroObj.ResponseData


sealed class NetworkResponses {

    open class Responses<T>(
        var status: String = "",
        val error_message: String = "",
        val results: T?
    )


    open class SearchResultResponse :
        Responses<MutableList<ResponseData.SearchResult>>(results = mutableListOf())


}