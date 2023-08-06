package com.ichin23.login.ui.home

import androidx.lifecycle.ViewModel
import com.ichin23.login.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
  repository: UserRepository,
) :ViewModel(){
  val users = repository.getAllUsers()
}