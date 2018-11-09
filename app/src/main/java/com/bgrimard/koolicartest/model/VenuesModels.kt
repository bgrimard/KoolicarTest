package com.bgrimard.koolicartest.model

//Generated with https://plugins.jetbrains.com/plugin/9960-json-to-kotlin-class-jsontokotlinclass-
data class VenuesResponse (
    val meta: Meta,
    val response: Response
)

data class Response(
    val confident: Boolean,
    val venues: List<Venue>
)

data class Venue(
    val categories: List<Category>,
    val hasPerk: Boolean,
    val id: String,
    val location: Location,
    val name: String,
    val referralId: String
)

data class Category(
    val icon: Icon,
    val id: String,
    val name: String,
    val pluralName: String,
    val primary: Boolean,
    val shortName: String
)

data class Icon(
    val prefix: String,
    val suffix: String
)

data class Location(
    val address: String,
    val cc: String,
    val city: String,
    val country: String,
    val distance: Int,
    val formattedAddress: List<String>,
    val labeledLatLngs: List<LabeledLatLng>,
    val lat: Double,
    val lng: Double,
    val state: String
)

data class LabeledLatLng(
    val label: String,
    val lat: Double,
    val lng: Double
)

data class Meta(
    val code: Int,
    val requestId: String
)

