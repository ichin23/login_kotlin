package com.ichin23.login.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ichin23.login.util.UiEvents
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
  onNavigate: (UiEvents.Navigate) -> Unit,
  viewModel: LoginViewModel = hiltViewModel()
) {

  LaunchedEffect(key1 = true) {
    viewModel.uiEvents.collect { event ->
      when (event) {
        is UiEvents.Navigate -> onNavigate(event)
        else -> Unit
      }
    }
  }

  Surface {
    Column(
      verticalArrangement = Arrangement.SpaceAround,
      modifier = Modifier
        .padding(10.dp)
        .fillMaxHeight()
    ) {
      Text(
        text = "LoginApp",
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 30.sp,
        modifier = Modifier.fillMaxWidth()
      )
      Column {

        OutlinedTextField(
          value = viewModel.email,
          onValueChange = { newEmail ->
            viewModel.onEvent(LoginScreenEvent.OnEmailChanged(newEmail))
          },
          label = { Text("Email") },
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
          value = viewModel.password,
          onValueChange = { newEmail ->
            viewModel.onEvent(LoginScreenEvent.OnEmailChanged(newEmail))
          },
          label = { Text("Senha") },
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        TextButton(
          onClick = {
            viewModel.onEvent(LoginScreenEvent.LoginWithEmailAndPassword)
          },
          shape = RoundedCornerShape(12.dp),
          colors = ButtonDefaults.textButtonColors(
            containerColor = MaterialTheme.colorScheme.primary
          ),
          modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
              BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
              shape = RoundedCornerShape(12.dp)
            )
        ) {
          Text(
            "Login",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
          )
        }
        TextButton(
          onClick = {
            viewModel.onEvent(LoginScreenEvent.NavigateCadastro)
          },
          shape = RoundedCornerShape(12.dp),

          modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
              BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
              shape = RoundedCornerShape(12.dp)
            )
        ) {
          Text(
            "Cadastre-se",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
          )
        }
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.End
        ) {

          TextButton(
            onClick = {
              viewModel.onEvent(LoginScreenEvent.NavigateEsqueceuSenha)
            },
            modifier = Modifier
              .padding(8.dp)
          ) {
            Text(
              "Esqueci a senha",
              color = MaterialTheme.colorScheme.tertiary,
              fontWeight = FontWeight.Bold,
              textAlign = TextAlign.End,
              fontSize = 18.sp
            )
          }
        }
      }
    }
  }
}