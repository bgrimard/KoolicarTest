package com.bgrimard.koolicartest.model

data class VenuesDetail(
    val meta: VenuesDetailMeta,
    val response: VenuesDetailResponse
)

data class VenuesDetailResponse(
    val venue: VenuesDetailVenue
)

data class VenuesDetailVenue(
    val categories: List<VenuesDetailCategory>,
    val id: String,
    val location: VenuesDetailLocation,
    val name: String,
    val price: VenuesDetailPrice?
)

data class VenuesDetailLocation(
    val cc: String,
    val country: String,
    val formattedAddress: List<String>,
    val labeledLatLngs: List<VenuesDetailLabeledLatLng>,
    val lat: Double,
    val lng: Double,
    val state: String
)

data class VenuesDetailLabeledLatLng(
    val label: String,
    val lat: Double,
    val lng: Double
)

data class VenuesDetailCategory(
    val icon: VenuesDetailIcon,
    val id: String,
    val name: String,
    val pluralName: String,
    val primary: Boolean,
    val shortName: String
)

data class VenuesDetailIcon(
    val prefix: String,
    val suffix: String
)

data class VenuesDetailPrice(
    val currency: String,
    val message: String,
    val tier: Int
)

data class VenuesDetailMeta(
    val code: Int,
    val requestId: String
)