package com.ichin23.login.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ichin23.login.util.UiEvents

@Composable
fun HomeScreen(
  onNavigate: (UiEvents.Navigate)->Unit,
  viewModel:HomeScreenViewModel = hiltViewModel()
){
  Surface {
    LazyColumn{

    }
  }
}