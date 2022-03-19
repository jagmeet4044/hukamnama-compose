package com.jagmeet.android.gurbaani.datasource.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HukamnamaDao {
    @Query("select * from HukamnamaInfoEntity")
    suspend fun getHukamNama(): HukamnamaInfoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert( videos:HukamnamaInfoEntity)
}