package com.ichin23.login.util

sealed class UiEvents{
  object PopBackStack: UiEvents()
  data class Navigate(val route: String): UiEvents()
}
