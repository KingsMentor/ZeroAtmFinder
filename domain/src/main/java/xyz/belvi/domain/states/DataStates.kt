package xyz.belvi.domain.states


sealed class DataStates
open class DataResponseError(var cause: Throwable? = null) : DataStates()
open class SearchStates : DataStates()



