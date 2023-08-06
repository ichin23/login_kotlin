package com.ichin23.login.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface UserRepository {

  suspend fun insertUser(user:User)

  suspend fun deleteUser(user:User)

  suspend fun updateUser(user: User)

  suspend fun queryUserByEmail(email:String): User?

  fun getAllUsers() : Flow<List<User>>
}