package com.ichin23.login.data

import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
  private val dao:UserDao
): UserRepository {
  override suspend fun deleteUser(user: User) {
    dao.deleteUser(user)
  }

  override suspend fun insertUser(user: User) {
    dao.insertUser(user)
  }

  override suspend fun queryUserByEmail(email: String): User? {
    return dao.queryUserByEmail(email)
  }

  override suspend fun updateUser(user: User) {
    dao.updateUser(user)
  }

  override fun getAllUsers(): Flow<List<User>> {
    return dao.getAllUsers()
  }
}