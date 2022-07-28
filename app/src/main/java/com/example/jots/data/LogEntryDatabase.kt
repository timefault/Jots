package com.example.jots.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jots.model.LogEntry

@Database(entities = [LogEntry::class], version = 1, exportSchema = false)
abstract class LogEntryDatabase: RoomDatabase() {

    abstract fun logEntryDao(): LogEntryDao

   companion object {
       @Volatile
       private var INSTANCE: LogEntryDatabase? = null

       fun getDatabase(context: Context): LogEntryDatabase {
           val tempInstance = INSTANCE
           if(tempInstance != null){ return tempInstance}
           synchronized(this) {
               val instance = Room.databaseBuilder(
                   context.applicationContext,
                   LogEntryDatabase::class.java,
                   "log_entry_database"
               ).build()
               INSTANCE = instance
               return instance
           }
       }
   }
}