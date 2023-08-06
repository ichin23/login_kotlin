package com.ichin23.login.ui.nova_senha

sealed class NovaSenhaEvents{
  data class OnSenhaChanged(val senha: String):NovaSenhaEvents()
  object OnButtonClick:NovaSenhaEvents()

}
