package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases

import io.reactivex.Observable
import hu.gyuriczaadam.reactivexpalygorund.data.operators_example.Task
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Singleton

@ViewModelScope
@Singleton
class CreateOperatorExampleUseCase {
    //Create observable from a single object
    operator fun invoke(task:Task):Observable<Task>{
        return Observable
            .create<Task> {
                if (!it.isDisposed) {
                    it.onNext(task)
                    it.onComplete()
                }
            }
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}