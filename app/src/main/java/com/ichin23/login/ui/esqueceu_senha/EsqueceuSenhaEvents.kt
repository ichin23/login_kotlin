package com.ichin23.login.ui.esqueceu_senha

sealed class EsqueceuSenhaEvents{
  data class OnEmailChanged(val email:String):EsqueceuSenhaEvents()
  data class OnButtonClick(val email:String):EsqueceuSenhaEvents()
  object OnPopBack:EsqueceuSenhaEvents()
}
