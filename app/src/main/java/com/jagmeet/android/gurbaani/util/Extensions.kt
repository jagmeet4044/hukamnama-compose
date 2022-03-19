package com.jagmeet.android.gurbaani.util

import com.jagmeet.android.gurbaani.datasource.database.HukamnamaEntity
import com.jagmeet.android.gurbaani.datasource.database.HukamnamaInfoEntity
import com.jagmeet.android.gurbaani.datasource.network.hukamnama.hukamnama_data.TodayHukamnama
import com.jagmeet.android.gurbaani.model.Hukamnama
import com.jagmeet.android.gurbaani.model.HukamnamaDetail

fun TodayHukamnama.toHukamnama(): HukamnamaDetail {
    var punjabi: MutableList<String> = emptyList<String>().toMutableList()
    var english: MutableList<String> = emptyList<String>().toMutableList()
    var punjabiExplanation: MutableList<String> = emptyList<String>().toMutableList()
    var englishExplanation: MutableList<String> = emptyList<String>().toMutableList()

    var hindi: MutableList<String> = emptyList<String>().toMutableList()

    val punjabiInfo = "${this.hukamnamainfo.raag.unicode} - ${this.hukamnamainfo.writer.unicode}"
    val englishInfo = "${this.hukamnamainfo.raag.english} - ${this.hukamnamainfo.writer.english}"

    for (hukamnamaList in this.hukamnama) {
        punjabi.add(hukamnamaList.line.gurmukhi.unicode)
        english.add(hukamnamaList.line.transliteration.english.text)
        hindi.add(hukamnamaList.line.transliteration.devanagari.text)
        punjabiExplanation.add(hukamnamaList.line.translation.punjabi.default.unicode)
        englishExplanation.add(hukamnamaList.line.translation.english.default)

    }
    val gregorianDate =
        "${this.date.gregorian.date}-${this.date.gregorian.month}-${this.date.gregorian.year}"
    return HukamnamaDetail(
        punjabi = Hukamnama(
            info =punjabiInfo,
            lines = punjabi,
            explanation = punjabiExplanation,
            gregorianDate = gregorianDate,
            nanakShahiDate = "${this.date.nanakshahi.punjabi.date} ${this.date.nanakshahi.punjabi.month}, ${this.date.nanakshahi.punjabi.year}"
        ),
        english = Hukamnama(
            info = englishInfo,
            lines = english,
            explanation = englishExplanation,
            gregorianDate = gregorianDate,
            nanakShahiDate = "${this.date.nanakshahi.english.date} ${this.date.nanakshahi.english.month}, ${this.date.nanakshahi.english.year}"
        )
    )


}

fun TodayHukamnama.asDbInfo() : HukamnamaInfoEntity {
    var punjabi: MutableList<String> = emptyList<String>().toMutableList()
    var english: MutableList<String> = emptyList<String>().toMutableList()
    var punjabiExplanation: MutableList<String> = emptyList<String>().toMutableList()
    var englishExplanation: MutableList<String> = emptyList<String>().toMutableList()

    var hindi: MutableList<String> = emptyList<String>().toMutableList()

    val punjabiInfo = "${this.hukamnamainfo.raag.unicode} - ${this.hukamnamainfo.writer.unicode}"
    val englishInfo = "${this.hukamnamainfo.raag.english} - ${this.hukamnamainfo.writer.english}"

    for (hukamnamaList in this.hukamnama) {
        punjabi.add(hukamnamaList.line.gurmukhi.unicode)
        english.add(hukamnamaList.line.transliteration.english.text)
        hindi.add(hukamnamaList.line.transliteration.devanagari.text)
        punjabiExplanation.add(hukamnamaList.line.translation.punjabi.default.unicode)
        englishExplanation.add(hukamnamaList.line.translation.english.default)

    }
    val gregorianDate =
        "${this.date.gregorian.date}-${this.date.gregorian.month}-${this.date.gregorian.year}"
    return HukamnamaInfoEntity(
        0,
        hukamnamaEntityPun = HukamnamaEntity(
            hukamnamaId ="",
            info =punjabiInfo,
            lines = punjabi,
            explanation = punjabiExplanation,
            gregorianDate = gregorianDate,
            nanakShahiDate = "${this.date.nanakshahi.punjabi.date} ${this.date.nanakshahi.punjabi.month}, ${this.date.nanakshahi.punjabi.year}"
        ),
        hukamnamaEntityEng = HukamnamaEntity(
            hukamnamaId ="",
            info = englishInfo,
            lines = english,
            explanation = englishExplanation,
            gregorianDate = gregorianDate,
            nanakShahiDate = "${this.date.nanakshahi.english.date} ${this.date.nanakshahi.english.month}, ${this.date.nanakshahi.english.year}"
        )
    )


}