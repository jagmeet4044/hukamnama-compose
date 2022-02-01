package com.jagmeet.android.gurbaani.business.util

import com.jagmeet.android.gurbaani.business.datasource.remote.api.hukamnamaData.TodayHukamnama
import com.jagmeet.android.gurbaani.business.model.*

fun TodayHukamnama.toHukamnama(): HukamnamaDetail {
    var punjabi: MutableList<String> = emptyList<String>().toMutableList()
    var english: MutableList<String> = emptyList<String>().toMutableList()
    var hindi: MutableList<String> = emptyList<String>().toMutableList()
    val punjabiInfo = "${this.hukamnamainfo.raag.unicode} - ${this.hukamnamainfo.writer.unicode}"
    val englishInfo = "${this.hukamnamainfo.raag.english} - ${this.hukamnamainfo.writer.english}"
    for (hukamnamaList in this.hukamnama) {
        punjabi.add(hukamnamaList.line.gurmukhi.unicode)
        english.add(hukamnamaList.line.transliteration.english.text)
        hindi.add(hukamnamaList.line.transliteration.devanagari.text)
    }
    val gregorianDate =
        "${this.date.gregorian.date}-${this.date.gregorian.month}-${this.date.gregorian.year}"
    return HukamnamaDetail(
        punjabi = Hukamnama(
            punjabiInfo,
            lines = punjabi,
            gregorianDate = gregorianDate,
            nanakShahiDate = "${this.date.nanakshahi.punjabi.date}-${this.date.nanakshahi.punjabi.month}-${this.date.nanakshahi.punjabi.year}"
        ),
        english = Hukamnama(
            englishInfo, lines = english,
            gregorianDate = gregorianDate,
            nanakShahiDate = "${this.date.nanakshahi.english.date}-${this.date.nanakshahi.english.month}-${this.date.nanakshahi.english.year}"
        )
    )


}