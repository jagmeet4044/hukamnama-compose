package com.jagmeet.android.gurbaani.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [HukamnamaInfoEntity::class, HukamnamaEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class HukamnamaDb : RoomDatabase() {

    abstract fun getHukamnamaDao(): HukamnamaDao

    companion object {
        val DATABASE_NAME: String = "hukamnama_db"
    }
}
