package hu.gyuriczaadam.reactivexpalygorund.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import hu.gyuriczaadam.reactivexpalygorund.presentation.main_screen.MainViewModel
import hu.gyuriczaadam.reactivexpalygorund.presentation.main_screen.components.PostItem

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val state = viewModel.state
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {
        //Text(text = "ToothPick test app: ${viewModel.returnRolledNum()}", textAlign = TextAlign.Center)
        Column(  modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)) {
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