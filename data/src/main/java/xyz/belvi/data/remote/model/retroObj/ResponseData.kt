package xyz.belvi.data.remote.model.retroObj


sealed class ResponseData {


    data class SearchResult(
        var vicinity: String = "",
        var reference: String = "",
        var place_id: String = "",
        var id: String = "",
        var name: String = "",
        var icon: String = "",
        var photo: Photos = Photos(),
        var geometry: Geometry = Geometry()
    )

    data class Photos(
        var height: Int = 0,
        var width: Int = 0,
        var photo_reference: String = ""
    )

    data class Geometry(
        var location: Location = Location()
    )

    data class Location(
        var lat: Double = 0.0,
        var lng: Double = 0.0
    )

}