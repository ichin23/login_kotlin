package com.ichin23.login.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
  @Insert(onConflict = OnConflictStrategy.ABORT)
  suspend fun insertUser(user:User)

  @Delete
  suspend fun deleteUser(user:User)

  @Update
  suspend fun updateUser(user: User)

  @Query("SELECT * from user WHERE email= :email")
  suspend fun queryUserByEmail(email:String):User?

  @Query("SELECT * FROM user")
  fun getAllUsers(): Flow<List<User>>
}