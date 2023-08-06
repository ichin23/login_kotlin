package com.ichin23.login.ui.cadastro

sealed class CadastroScreenEvents{
  object OnClickCadastra:CadastroScreenEvents()
  data class OnEmailChanged(val email:String):CadastroScreenEvents()
  data class OnNomeChanged(val nome:String):CadastroScreenEvents()
  data class OnPasswordChanged(val password:String):CadastroScreenEvents()
  object OnClickBackLogin:CadastroScreenEvents()
}
