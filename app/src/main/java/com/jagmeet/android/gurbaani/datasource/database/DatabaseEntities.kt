package com.jagmeet.android.gurbaani.datasource.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jagmeet.android.gurbaani.model.Hukamnama
import com.jagmeet.android.gurbaani.model.HukamnamaDetail


@Entity
class HukamnamaEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = "id") val hukamnamaId: String,
    val info: String = "",
    val gregorianDate: String = "",
    val nanakShahiDate: String = "",
    val lines: List<String> = emptyList(),
    val explanation: List<String> = emptyList()
)

@Entity
class HukamnamaInfoEntity constructor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "info_id") val hukamnamaInfoId: Int,
    @Embedded(prefix = "eng_")
    val hukamnamaEntityEng: HukamnamaEntity,
    @Embedded(prefix = "pun_")
    val hukamnamaEntityPun: HukamnamaEntity
)

public fun HukamnamaInfoEntity.asDomainModel(): HukamnamaDetail {
    return HukamnamaDetail(
        english = Hukamnama(
            hukamnamaEntityEng.info,
            hukamnamaEntityEng.gregorianDate,
            hukamnamaEntityEng.nanakShahiDate,
            hukamnamaEntityEng.lines,
            hukamnamaEntityEng.explanation,
        ),
        punjabi = Hukamnama(
            hukamnamaEntityPun.info,
            hukamnamaEntityPun.gregorianDate,
            hukamnamaEntityPun.nanakShahiDate,
            hukamnamaEntityPun.lines,
            hukamnamaEntityPun.explanation,
        ))
}