package com.example.jots.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jots.model.LogEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface LogEntryDao {
    @Query("SELECT * FROM log_entry_table ORDER BY id DESC")
    fun getAllEntries(): Flow<List<LogEntry>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEntry(entry: LogEntry)
    @Update
    suspend fun updateEntry(entry: LogEntry)
    @Delete
    suspend fun deleteEntry(entry: LogEntry)
    @Query("DELETE FROM log_entry_table")
    suspend fun deleteAllEntries()
    @Query("SELECT * FROM  log_entry_table WHERE content LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<LogEntry>>
}