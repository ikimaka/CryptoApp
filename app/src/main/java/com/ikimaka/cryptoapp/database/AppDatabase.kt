package com.ikimaka.cryptoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ikimaka.cryptoapp.pojo.CoinPriceInfo

@Database(entities = [CoinPriceInfo::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        private const val DB_NAME = "main.db"
        private var db: AppDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room
                    .databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                    //.fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun coinPriceInfoDao(): CoinPriceInfoDao

}