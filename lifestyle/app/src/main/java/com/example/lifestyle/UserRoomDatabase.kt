package com.example.lifestyle

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Room
import java.util.concurrent.Executors

@Database(entities = arrayOf(UserTable::class), version = 1)
abstract class UserRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var mInstance: UserRoomDatabase? = null
        val databaseExecutor = Executors.newFixedThreadPool(4)

        @Synchronized
        fun getInstance(ctx: Context): UserRoomDatabase {
            if (mInstance == null)
                mInstance = Room.databaseBuilder(
                    ctx.applicationContext, UserRoomDatabase::class.java,
                    "UserDataBase"
                )
                    .fallbackToDestructiveMigration()
                    .build()

            return mInstance!!

        }


        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Empty implementation, because the schema isn't changing.
            }
        }
    }
}
