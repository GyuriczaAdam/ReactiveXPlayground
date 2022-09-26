package hu.gyuriczaadam.reactivexpalygorund.presentation.transformation_operators_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import hu.gyuriczaadam.reactivexpalygorund.di.AppModule
import hu.gyuriczaadam.reactivexpalygorund.util.AppConsants
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers.io
import toothpick.InjectConstructor
import java.util.concurrent.TimeUnit

@InjectConstructor
class TransformationsViewModel(
    private val appModule: AppModule,
) : ViewModel() {
    var state by mutableStateOf(TransformationScreenState())
        private set
    var disposables = CompositeDisposable()
    var timeSinceLastRequest:Long = 0


    init {
        timeSinceLastRequest = System.currentTimeMillis()
    }

    @SuppressLint("CheckResult")
    fun onEvent(event: TransformationEvent){

        when(event){
            is TransformationEvent.OnQueryChange -> {
                state = state.copy(query = event.query)
                Observable
                    .create { emitter ->
                        if (!emitter.isDisposed) {
                            emitter.onNext(state.query)
                        }
                    }
                    .debounce(5000,TimeUnit.MILLISECONDS)
                    .subscribeOn(io())
                   .subscribe(
                       {
                           Log.d(AppConsants.TAG,"Time since the last request: ${System.currentTimeMillis()}")
                           Log.d(AppConsants.TAG,"The query is : $it")
                         timeSinceLastRequest = System.currentTimeMillis()
                           searchTasks(it)
                       },
                       {
                           Log.e(AppConsants.TAG,"onError: ${it.message}")
                       },
                       {
                           Log.d(AppConsants.TAG, "Buffer task completed")
                       },
                       {
                        disposables.add(it)
                       }
                   )
            }
            TransformationEvent.OnSearch -> {

            }
        }
    }

    fun searchTasks(query:String){
        Log.d(AppConsants.TAG, "Searching in tasks")
    }



}