package com.san.archapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.san.archapp.data.entity.Data
import com.san.archapp.data.local.UserDao


@Database(
    entities = [Data::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}
