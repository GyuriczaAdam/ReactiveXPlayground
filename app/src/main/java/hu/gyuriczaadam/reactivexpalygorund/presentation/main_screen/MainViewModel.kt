package hu.gyuriczaadam.reactivexpalygorund.presentation.main_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import hu.gyuriczaadam.reactivexpalygorund.di.AppModule
import hu.gyuriczaadam.reactivexpalygorund.util.AppConsants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import toothpick.InjectConstructor

@SuppressLint("CheckResult")
@InjectConstructor
class MainViewModel(
    private val appModule: AppModule,
) : ViewModel(){
    var state by mutableStateOf(MainScreenState())
        private set
    init {
        state = state.copy(isLoading = true)

        getDataFromApi()
        appModule.provideCreateObservableFromTask()
        appModule.provideJustOperatorTestUseCase()
        appModule.provideCreateObservableFromListOfObjectsUseCase()
        appModule.provideRangeOperatorTestUseCase()
        appModule.provideFlowableExample()
        getIntervalExample()
        makeFutureQuery()
        //makeConverterExample()
    }

    private fun getDataFromApi(){
        appModule.provideGetPostsUseCase()
            ?.flatMap { t ->
                state = state.copy(posts = t)
                Observable.fromIterable(t)
                    .subscribeOn(io())
            }
            ?.subscribe(
                {
                },
                {
                    Log.e(AppConsants.TAG,"onError: ${it.message}")
                    state = state.copy(error = "Error happend: ${it.message}")
                },
                {
                    Log.d(AppConsants.TAG, "Task completed")

                    state = state.copy(isLoading = false)
                }
            )
    }

    private fun getIntervalExample(){
        appModule.provideIntervalExample()
            .subscribeOn(io())
            .takeWhile {
                return@takeWhile it <= 5
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {interval->
                Log.d(AppConsants.TAG,"This is the task interval/timer: $interval")
                },
                {
                    Log.e(AppConsants.TAG,"onError: ${it.message}")
                },
                {
                    Log.d(AppConsants.TAG, "Task completed")
                })
    }

    private fun makeFutureQuery(){
        appModule.provideFromFutureRepostiory().makeFutureQuery()
            .get()
            ?.subscribeOn(io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe (
                {
                   Log.d(AppConsants.TAG, "onNext: ${it?.string()}")
                },
                {
                        Log.e(AppConsants.TAG,"onError: ${it.message}")
                    },
                    {
                        Log.d(AppConsants.TAG, "Task completed")
                    }
                    )
        }

  fun makeConverterExample(){
        appModule.provideLiveDataConverterUseCase()
        }
}

