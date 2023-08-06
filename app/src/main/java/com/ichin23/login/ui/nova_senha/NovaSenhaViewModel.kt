package com.ichin23.login.ui.nova_senha

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
class NovaSenhaViewModel @Inject constructor(
  val repository: UserRepository
) :ViewModel(){
  var senha by mutableStateOf("")
    private set

  val _uiEvent = Channel<UiEvents>()
  val uiEvents= _uiEvent.receiveAsFlow()

  fun onEvent(event: NovaSenhaEvents){
    when(event){
      is NovaSenhaEvents.OnSenhaChanged->senha=event.senha
      is NovaSenhaEvents.OnButtonClick->{
        sendUiEvent(UiEvents.Navigate(Routes.HOME))
      }
    }
  }

  fun sendUiEvent(event:UiEvents){
    viewModelScope.launch {
      _uiEvent.send(event)
    }
  }
}