package com.example.lifestyle

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Room





class RoomDBClass {
    @Database(entities = arrayOf(RoomUserClass.User::class), version = 1)
    abstract class UsersDatabase : RoomDatabase() {
        abstract fun userDao(): DAORoom


        companion object {
            private var instance: UsersDatabase? = null

            @Synchronized
            fun getInstance(ctx: Context): UsersDatabase {
                if (instance == null)
                    instance = Room.databaseBuilder(
                        ctx.applicationContext, UsersDatabase::class.java,
                        "UserDataBase"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                return instance!!

            }

//            private val roomCallback = object : Callback() {
//                override suspend fun onCreate(db: SupportSQLiteDatabase) {
//                    super.onCreate(db)
//                    populateDatabase(instance!!)
//                }
//            }
//
//            private suspend fun populateDatabase(db: UsersDatabase) {
//                val roomDao = db.userDao()
//                roomDao.insertAll(
//                    RoomUserClass.User(
//                        "10294031",
//                        "braden",
//                        "mcclean",
//                        25,
//                        1,
//                        6,
//                        1,
//                        220,
//                        "lehi",
//                        "usa",
//                        null,
//                        null,
//                        1,
//                        200,
//                        300,
//                        false,
//                        2103,
//                        23.0f
//                    )
//                )
//
//
//            }
//        }

            val MIGRATION_1_2 = object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    // Empty implementation, because the schema isn't changing.
                }
            }
        }
    }
}