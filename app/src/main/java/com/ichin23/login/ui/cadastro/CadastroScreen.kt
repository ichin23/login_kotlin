package com.ichin23.login.ui.cadastro

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ichin23.login.ui.login.LoginScreenEvent
import com.ichin23.login.util.UiEvents
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroScreen(
  onNavigateClearPop: (UiEvents.Navigate)->Unit,
  onPopBackStack: ()->Unit,
  viewModel: CadastroScreenViewModel = hiltViewModel()
){

  LaunchedEffect(key1 = true){
    viewModel.uiEvent.collect{event->
      when (event){
        is UiEvents.PopBackStack -> onPopBackStack()
        is UiEvents.Navigate -> onNavigateClearPop(event)
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
          value = viewModel.nome,
          onValueChange = { newNome ->
            viewModel.onEvent(CadastroScreenEvents.OnNomeChanged(newNome))
          },
          label = { Text("Nome") },
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
          value = viewModel.email,
          onValueChange = { newEmail ->
            viewModel.onEvent(CadastroScreenEvents.OnEmailChanged(newEmail))
          },
          label = { Text("Email") },
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
          value = viewModel.senha,
          onValueChange = { newPassword ->
            viewModel.onEvent(CadastroScreenEvents.OnPasswordChanged(newPassword))
          },
          label = { Text("Senha") },
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        TextButton(
          onClick = {
            viewModel.onEvent(CadastroScreenEvents.OnClickCadastra)
          },
          shape = RoundedCornerShape(12.dp),
          colors= ButtonDefaults.textButtonColors(
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
            "Cadastrar",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
          )
        }
        TextButton(
          onClick = {
            viewModel.onEvent(CadastroScreenEvents.OnClickBackLogin)
          },

          modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

        ) {
          Text(
            "Voltar para Login",
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.Bold,
            textAlign=TextAlign.End,
            fontSize = 18.sp
          )
        }
      }
    }
  }

}