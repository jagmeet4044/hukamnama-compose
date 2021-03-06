package com.jagmeet.android.gurbaani.model

data class HukamnamaDetail(
    val english: Hukamnama = Hukamnama(),
    val punjabi: Hukamnama = Hukamnama()
)

class Hukamnama(
    val info: String = "",
    val gregorianDate: String = "",
    val nanakShahiDate: String = "",
    val lines: List<String> = emptyList(),
    val explanation: List<String> = emptyList()
)