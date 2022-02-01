package com.jagmeet.android.gurbaani.business.util

import com.jagmeet.android.gurbaani.business.datasource.remote.api.hukamnamaData.TodayHukamnama
import com.jagmeet.android.gurbaani.business.model.Hukamnama

fun TodayHukamnama.toHukamnama(): Hukamnama {
    var punjabi: MutableList<String> = emptyList<String>().toMutableList()
    var english: MutableList<String> = emptyList<String>().toMutableList()
    var hindi: MutableList<String> = emptyList<String>().toMutableList()
    for (hukamnamaList in this.hukamnama) {
        punjabi.add(hukamnamaList.line.gurmukhi.unicode)
        english.add(hukamnamaList.line.transliteration.english.text)
        hindi.add(hukamnamaList.line.transliteration.devanagari.text)
    }
    return Hukamnama(punjabi, english, hindi)

}