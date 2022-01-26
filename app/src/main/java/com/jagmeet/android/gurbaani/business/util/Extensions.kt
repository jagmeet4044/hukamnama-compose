package com.jagmeet.android.gurbaani.business.util

import com.jagmeet.android.gurbaani.business.datasource.remote.api.hukamnamaData.TodayHukamnama
import com.jagmeet.android.gurbaani.business.model.Hukamnama
import kotlin.text.StringBuilder

fun TodayHukamnama.toHukamnama(): Hukamnama {
    var hukamnama = StringBuilder()
    for (hukamnamaList in this.hukamnama) {
        hukamnama.append(hukamnamaList.line.gurmukhi.unicode)
        hukamnama.append(System.getProperty("line.separator"))
    }
    return Hukamnama(hukamnama.toString())

}