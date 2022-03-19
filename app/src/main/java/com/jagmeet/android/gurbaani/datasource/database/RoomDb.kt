package com.jagmeet.android.gurbaani.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [HukamnamaInfoEntity::class, HukamnamaEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class HukamnamaDb : RoomDatabase() {
    abstract val hukamnamaDao: HukamnamaDao
}

private lateinit var INSTANCE: HukamnamaDb


fun getDatabase(context: Context): HukamnamaDb {
    synchronized(HukamnamaDb::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                HukamnamaDb::class.java,
                "hukamnamas"
            ).build()
        }
    }
    return INSTANCE
}
