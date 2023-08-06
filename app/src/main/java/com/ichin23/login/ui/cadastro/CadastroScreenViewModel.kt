package com.ichin23.login.ui.cadastro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ichin23.login.data.User
import com.ichin23.login.data.UserRepository
import com.ichin23.login.util.Routes
import com.ichin23.login.util.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CadastroScreenViewModel @Inject constructor(
  private val repository: UserRepository,
  savedStateHandle: SavedStateHandle,
):ViewModel() {
  var nome by mutableStateOf("")
    private set
  var email by mutableStateOf("")
    private set
  var senha by mutableStateOf("")
    private set


  private val _uiEvents = Channel<UiEvents>()
  val uiEvent = _uiEvents.receiveAsFlow()

  fun onEvent(event: CadastroScreenEvents){
    when(event){
      is CadastroScreenEvents.OnClickCadastra->{
        viewModelScope.launch {
          repository.insertUser(
            User(
              name = nome,
              email = email,
              password = senha,
            )
          )
        }
        sendUiEvent(UiEvents.Navigate(Routes.HOME))
      }
      is CadastroScreenEvents.OnEmailChanged->{
        email=event.email
      }
      is CadastroScreenEvents.OnNomeChanged->{
        nome=event.nome
      }
      is CadastroScreenEvents.OnPasswordChanged->{
        senha=event.password
      }
      is CadastroScreenEvents.OnClickBackLogin->{
        sendUiEvent(UiEvents.PopBackStack)
      }
    }
  }

  private fun sendUiEvent(event: UiEvents){
    viewModelScope.launch {
      _uiEvents.send(event)
    }
  }
}