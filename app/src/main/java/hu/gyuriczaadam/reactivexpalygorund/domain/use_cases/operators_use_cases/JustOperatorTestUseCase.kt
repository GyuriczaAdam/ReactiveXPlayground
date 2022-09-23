package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.operators_use_cases

import android.util.Log
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import hu.gyuriczaadam.reactivexpalygorund.util.AppConsants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Singleton

@ViewModelScope
@Singleton
class JustOperatorTestUseCase {
    operator fun invoke(): Disposable? {
        //With just operator you can create an observable with the maximum of 10 objects
        return Observable.just("one","two","three","four")
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {task->
                    Log.d(AppConsants.TAG,"This is the task: $task")
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