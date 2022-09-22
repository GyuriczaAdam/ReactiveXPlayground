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
import java.util.*
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
           ?.flatMap { t ->
               state = state.copy(posts = t)
               Observable.fromIterable(t)
                   .subscribeOn(Schedulers.io())
           }
           ?.subscribe(
               {post->
                 },
               {
                   Log.e("TAG","onError: ${it.message}")
                   state = state.copy(error = "Error happend: ${it.message}")
               },
               {
                 Log.d("Tag", "Task completed")

                   state = state.copy(isLoading = false)
               }
               )
        getObservableFromObject()
        getObservableFromListOfObjects()
    }

    fun getObservableFromObject(){
        appModule.provideCreateObservableFromTask()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {task->
                    Log.d("Tag","This is the task: ${task.description}")
                },
                {
                    Log.e("TAG","onError: ${it.message}")
                },
                {
                    Log.d("Tag", "Task completed")
                }
            )
    }

    fun getObservableFromListOfObjects(){
        appModule.provideCreateObservableFromListOfObjectsUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {task->
                    Log.d("Tag","This is the task: ${task.description}")
                },
                {
                    Log.e("TAG","onError: ${it.message}")
                },
                {
                    Log.d("Tag", "Task completed")
                }
            )
    }
}