package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases

import io.reactivex.Observable
import hu.gyuriczaadam.reactivexpalygorund.data.operators_example.Task
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Singleton

@ViewModelScope
@Singleton
class CreateObservableFromListOperatorExampleUseCase {
    //Create observable from a list of objects
    operator fun invoke(tasks:List<Task>):Observable<Task>{
        return Observable
            .create<Task> {
                for (task in tasks){
                    if (!it.isDisposed) {
                        it.onNext(task)

                    }
                }
                if(!it.isDisposed){
                    it.onComplete()
                }
            }
    }
}