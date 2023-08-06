package com.ichin23.login.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User (
  val name:String,
  @PrimaryKey val email:String,
  val password: String,
)