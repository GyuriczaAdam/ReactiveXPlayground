package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.operators_use_cases

import android.util.Log
import hu.gyuriczaadam.reactivexpalygorund.data.operators_example.Task
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import hu.gyuriczaadam.reactivexpalygorund.util.AppConsants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Singleton

@ViewModelScope
@Singleton
class RangeOperatorExampleUseCase {
    operator fun invoke(): Disposable? {
        //The observer will loop trough in the given range and on each iteration a heavy task can be done on a background thread
        return Observable
            .range(0,9)
            .subscribeOn(io())
            .map(Function {
                Log.d(AppConsants.TAG,"A heavy task can be done $it times on: ${Thread.currentThread().name}")
                return@Function Task("This is a heavy task with the prioriy of:",true,it)
            })
            .takeWhile(Predicate {
            return@Predicate it.priority < 9 }
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {task->
                    Log.d(AppConsants.TAG,"This is the task range operator: $task")
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