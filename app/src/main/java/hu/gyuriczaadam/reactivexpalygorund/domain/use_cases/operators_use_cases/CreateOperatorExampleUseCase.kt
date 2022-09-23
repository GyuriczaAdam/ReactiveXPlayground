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
class CreateOperatorExampleUseCase {
    //Create observable from a single object
    operator fun invoke(task:Task): Disposable? {
        return Observable
            .create<Task> {
                if (!it.isDisposed) {
                    it.onNext(task)
                    it.onComplete()
                }
            }
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {task->
                    Log.d(AppConsants.TAG,"This is the task: ${task.description}")
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