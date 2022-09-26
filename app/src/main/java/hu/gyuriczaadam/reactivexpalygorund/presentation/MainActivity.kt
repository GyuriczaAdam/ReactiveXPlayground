package hu.gyuriczaadam.reactivexpalygorund

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hu.gyuriczaadam.reactivexpalygorund.di.ActivityScope
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import hu.gyuriczaadam.reactivexpalygorund.presentation.MainScreen
import hu.gyuriczaadam.reactivexpalygorund.presentation.main_screen.MainViewModel
import hu.gyuriczaadam.reactivexpalygorund.presentation.Screen
import hu.gyuriczaadam.reactivexpalygorund.presentation.ui.theme.ReactiveXPalygorundTheme
import hu.gyuriczaadam.reactivexpalygorund.util.AppConsants
import toothpick.Scope
import toothpick.ktp.KTP
import toothpick.smoothie.lifecycle.closeOnDestroy
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getScope()
            .openSubScope(ViewModelScope::class.java)
            .supportScopeAnnotation(ViewModelScope::class.java)
            .closeOnDestroy(this)
            .inject(this)
        setContent {
            ReactiveXPalygorundTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.MainScreen.route)
                {
                    composable(route = Screen.MainScreen.route){
                        MainScreen(navController,mainViewModel)
                    }
                }
            }
        }
    }
    open fun getScope(): Scope {
        return KTP.openRootScope()
            .openSubScope(AppConsants.APPSCOPE)
            .openSubScope(ActivityScope::class.java)
    }
}
