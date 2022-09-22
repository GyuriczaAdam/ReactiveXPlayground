package hu.gyuriczaadam.reactivexpalygorund.presentation.main_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import hu.gyuriczaadam.reactivexpalygorund.di.AppModule
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import toothpick.InjectConstructor
import javax.inject.Singleton

@SuppressLint("CheckResult")
@Singleton
@InjectConstructor
@ViewModelScope
class MainViewModel(
    private val appModule: AppModule,
) : ViewModel() {
    var state by mutableStateOf(MainScreenState())
        private set
    init {
        state = state.copy(isLoading = true)
       appModule.provideGetPostsUseCase()
           ?.subscribeOn(Schedulers.io())
           ?.observeOn(AndroidSchedulers.mainThread())
           ?.flatMap { t ->
               Log.d("TAG","MY TEYTS: ${t.size}")
               state = state.copy(posts = t)
               Observable.fromIterable(t)
                   .subscribeOn(Schedulers.io())
           }
           ?.subscribe(
               {post->
                   Log.d("Tag", "Post: ${post?.body}")
                 },
               {
                   Log.e("TAG","onError: ${it.message}")
               },
               {
                 Log.d("Tag", "Task completed")
                   state = state.copy(isLoading = false)
               }
               )
    }
    fun returnRolledNum():Int{
        return appModule.providerRandIntUseCase()
    }
}