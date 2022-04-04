package com.san.archapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.san.archapp.data.entity.Data
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user_data")
    fun getUsersList(): Flow<List<Data>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: List<Data>)

    @Query("DELETE FROM user_data")
    suspend fun deleteAll()

    @Query("SELECT comments FROM user_data WHERE id =:id")
    fun getComment(id: Int): Flow<String?>

    @Query("UPDATE user_data SET comments = :comment WHERE id=:id")
    suspend fun insertComment(id: Int, comment: String)
}