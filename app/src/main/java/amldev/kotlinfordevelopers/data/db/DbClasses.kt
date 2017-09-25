package amldev.kotlinfordevelopers.data.db

/***************************************************************************************************
 * Created by anartzmugika on 25/9/17.
 *  Map database tables properties. Use with equal name in definition
 **************************************************************************************************/


class TownInfo(var map: MutableMap<String, Any?>) {
    // Properties must be equal tables columns names
    var _id: String by map
    var city: String by map
    var country: String by map
    var lat: String by map
    var lng: String by map

    constructor(_id: String, city: String, country: String, lat: String, lng: String) : this(HashMap()) {
        this._id = _id
        this.city = city
        this.country = country
        this.lat = lat
        this.lng = lng
    }
}