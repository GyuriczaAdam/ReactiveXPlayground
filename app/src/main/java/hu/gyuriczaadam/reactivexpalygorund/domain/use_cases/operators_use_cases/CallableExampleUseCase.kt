package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.operators_use_cases

import hu.gyuriczaadam.reactivexpalygorund.data.operators_example.Task
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import io.reactivex.Observable
import java.util.concurrent.Callable
import javax.inject.Singleton

@ViewModelScope
@Singleton
class CallableExampleUseCase {
    /*operator fun invoke():Observable<Task>{
        //Create observable
        /*return Observable
        //return data from database on a background thread
            .fromCallable(Callable {task->
            })*/
    }*/
}