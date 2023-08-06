package com.ichin23.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ichin23.login.ui.cadastro.CadastroScreen
import com.ichin23.login.ui.esqueceu_senha.EsqueceuSenhaScreen
import com.ichin23.login.ui.home.HomeScreen
import com.ichin23.login.ui.login.LoginScreen
import com.ichin23.login.ui.nova_senha.NovaSenhaScreen
import com.ichin23.login.ui.theme.LoginTheme
import com.ichin23.login.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      LoginTheme {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Routes.LOGIN) {
          composable(Routes.LOGIN) {
            LoginScreen({navigate -> navController.navigate(navigate.route) })
          }
          composable(Routes.CADASTRO){
            CadastroScreen(
              onNavigateClearPop = {event->
                navController.clearBackStack(event.route)
              },
              onPopBackStack = {navController.popBackStack()}
            )
          }
          composable(Routes.ESQUECEU_SENHA){
            EsqueceuSenhaScreen(onPopBackStack = {navController.popBackStack()})
          }
          composable(Routes.NOVA_SENHA){
            NovaSenhaScreen(
              onNavigateClearPop = {event->
                navController.clearBackStack(event.route)
              },
              onPopBackStack = { navController.popBackStack() }
            )
          }
          composable((Routes.HOME)){
            HomeScreen({navigate -> navController.navigate(navigate.route) })
          }
        }
      }
    }
  }
}
