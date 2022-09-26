package hu.gyuriczaadam.reactivexpalygorund.presentation.transformation_operators_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hu.gyuriczaadam.reactivexpalygorund.presentation.main_screen.MainViewModel
import hu.gyuriczaadam.reactivexpalygorund.presentation.main_screen.components.PostItem
import hu.gyuriczaadam.reactivexpalygorund.util.LocalSpacing

@Composable
fun TransfroamtionOperatorsScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val state = viewModel.state
    val localspacing = LocalSpacing.current

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(  modifier = Modifier
            .fillMaxSize()
            .padding(localspacing.spaceMedium)) {

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Transformations Screen", textAlign = TextAlign.Center, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(localspacing.spaceLarge))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.posts){posts->
                    PostItem(post = posts!!)
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> CircularProgressIndicator()
            state.error.isNotBlank() ->{
                Text(
                    text = state.error,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
            }
            state.posts.isEmpty()->{
                Text(
                    text = "No results",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
    
}