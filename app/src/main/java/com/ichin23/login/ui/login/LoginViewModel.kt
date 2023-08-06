package com.ichin23.login.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ichin23.login.data.UserRepository
import com.ichin23.login.util.Routes
import com.ichin23.login.util.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val repository: UserRepository,
  savedStateHandle: SavedStateHandle
) : ViewModel() {
  var email by mutableStateOf("")
    private set

  var password by mutableStateOf("")
    private set

  val _uiEvents = Channel<UiEvents>()
  val uiEvents = _uiEvents.receiveAsFlow()

  fun onEvent(event: LoginScreenEvent) {
    when (event) {
      is LoginScreenEvent.LoginWithEmailAndPassword -> {}
      is LoginScreenEvent.NavigateCadastro -> {
        sendUiEvent(UiEvents.Navigate(Routes.CADASTRO))
      }
      is LoginScreenEvent.NavigateEsqueceuSenha -> {
        sendUiEvent(UiEvents.Navigate(Routes.ESQUECEU_SENHA))
      }
      is LoginScreenEvent.OnEmailChanged->{
        email=event.email;
      }
      is LoginScreenEvent.OnPasswordChanged->{
        password=event.password
      }
    }
  }

  fun sendUiEvent(event: UiEvents){
    viewModelScope.launch {
      _uiEvents.send(event)
    }
  }
}