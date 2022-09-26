package hu.gyuriczaadam.reactivexpalygorund.presentation.transformation_operators_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import hu.gyuriczaadam.reactivexpalygorund.di.AppModule
import io.reactivex.disposables.CompositeDisposable
import toothpick.InjectConstructor

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

    fun onEvent(event: TransformationEvent){
        when(event){
            is TransformationEvent.OnQueryChange -> {
                state = state.copy(query = event.query)
            }
            TransformationEvent.OnSearch -> {

            }
        }
    }

    fun searchTasks(){

    }



}