package com.ichin23.login.ui.login

sealed class LoginScreenEvent {
    object LoginWithEmailAndPassword:LoginScreenEvent()
    object NavigateCadastro: LoginScreenEvent()
    object NavigateEsqueceuSenha: LoginScreenEvent()
    class OnEmailChanged(val email: String):LoginScreenEvent()
    class OnPasswordChanged(val password: String):LoginScreenEvent()
}