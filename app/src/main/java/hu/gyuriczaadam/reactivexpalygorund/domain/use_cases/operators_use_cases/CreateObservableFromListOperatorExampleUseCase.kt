package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.operators_use_cases

import android.util.Log
import io.reactivex.Observable
import hu.gyuriczaadam.reactivexpalygorund.data.operators_example.Task
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import hu.gyuriczaadam.reactivexpalygorund.util.AppConsants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Singleton

@ViewModelScope
@Singleton
class CreateObservableFromListOperatorExampleUseCase {
    //Create observable from a list of objects
    operator fun invoke(tasks:List<Task>): Disposable? {
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
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(

                {task->
                    Log.d(AppConsants.TAG,"This is the task refactored: ${task.description}")
                },
            {
            Log.e(AppConsants.TAG,"onError: ${it.message}")
            },
            {
            Log.d(AppConsants.TAG, "Task completed")
            }
            )
    }
}