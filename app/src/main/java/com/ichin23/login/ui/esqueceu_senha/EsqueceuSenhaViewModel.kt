package com.ichin23.login.ui.esqueceu_senha

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ichin23.login.data.UserRepository
import com.ichin23.login.ui.cadastro.CadastroScreenEvents
import com.ichin23.login.util.Routes
import com.ichin23.login.util.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EsqueceuSenhaViewModel @Inject constructor (
  private val repository: UserRepository,
  savedStateHandle: SavedStateHandle
) :ViewModel() {
  var email by mutableStateOf("")
    private set

  val _uiEvents = Channel<UiEvents>()
  val uiEvents = _uiEvents.receiveAsFlow()

  fun onEvent(event: EsqueceuSenhaEvents){
    when(event){
      is EsqueceuSenhaEvents.OnEmailChanged ->email=event.email
      is EsqueceuSenhaEvents.OnPopBack->sendUiEvent(UiEvents.PopBackStack)
      is EsqueceuSenhaEvents.OnButtonClick->{
        viewModelScope.launch {
          repository.queryUserByEmail(event.email)?.let { user->
            sendUiEvent(UiEvents.Navigate(Routes.HOME))
          }
        }

        sendUiEvent(UiEvents.Navigate(Routes.NOVA_SENHA+"?email=${event.email}"))
      }
    }
  }

  fun sendUiEvent(event:UiEvents){
    viewModelScope.launch {
      _uiEvents.send(event)
    }
  }
}