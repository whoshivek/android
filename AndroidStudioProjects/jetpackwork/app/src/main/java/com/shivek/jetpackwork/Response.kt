package com.shivek.jetpackwork

data class Response(
	val current: Current? = null,
	val location: Location? = null
)

data class Location(
	val localtime: String? = null,
	val country: String? = null,
	val localtimeEpoch: Int? = null,
	val name: String? = null,
	val lon: Double? = null,
	val region: String? = null,
	val lat: Double? = null,
	val tzId: String? = null
)

data class Condition(
	val code: Int? = null,
	val icon: String? = null,
	val text: String? = null
)

data class Current(
	val feelslikeC: Double? = null,
	val uv: Double? = null,
	val lastUpdated: String? = null,
	val feelslikeF: Double? = null,
	val windDegree: Int? = null,
	val lastUpdatedEpoch: Int? = null,
	val isDay: Int? = null,
	val precipIn: Double? = null,
	val windDir: String? = null,
	val gustMph: Double? = null,
	val tempC: Double? = null,
	val pressureIn: Double? = null,
	val gustKph: Double? = null,
	val tempF: Double? = null,
	val precipMm: Double? = null,
	val cloud: Int? = null,
	val windKph: Double? = null,
	val condition: Condition? = null,
	val windMph: Double? = null,
	val visKm: Double? = null,
	val humidity: Int? = null,
	val pressureMb: Double? = null,
	val visMiles: Double? = null
)

