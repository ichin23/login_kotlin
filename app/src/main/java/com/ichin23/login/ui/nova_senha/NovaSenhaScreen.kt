package com.ichin23.login.ui.nova_senha

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.ichin23.login.ui.esqueceu_senha.EsqueceuSenhaEvents
import com.ichin23.login.util.UiEvents

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NovaSenhaScreen(
  onNavigateClearPop: (UiEvents.Navigate)->Unit,
  onPopBackStack: ()->Unit,
  viewModel: NovaSenhaViewModel = hiltViewModel()
){
  LaunchedEffect(key1 = true){
    viewModel.uiEvents.collect{event->
      when(event){
        is UiEvents.Navigate->onNavigateClearPop(event)
        is UiEvents.PopBackStack->onPopBackStack()
      }
    }

  }

  Surface {
    Column (
      modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
      verticalArrangement = Arrangement.SpaceAround
    ){
      Text(
        "Nova Senha",
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 30.sp,
        modifier = Modifier.fillMaxWidth()
      )
      Column {


        OutlinedTextField(
          value = viewModel.senha,
          onValueChange = { novaSenha ->
            viewModel.onEvent(NovaSenhaEvents.OnSenhaChanged(novaSenha))
          },
          label = { Text("Email") },

          shape = RoundedCornerShape(12.dp),
          modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(35.dp))
        TextButton(
          onClick = {
            viewModel.onEvent(NovaSenhaEvents.OnButtonClick)
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
            text = "Alterar",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
          )
        }
      }

    }
  }
}