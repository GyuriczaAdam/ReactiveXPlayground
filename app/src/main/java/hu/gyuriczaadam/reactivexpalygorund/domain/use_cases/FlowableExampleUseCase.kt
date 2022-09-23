package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases

import android.util.Log
import hu.gyuriczaadam.reactivexpalygorund.data.operators_example.Task
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import hu.gyuriczaadam.reactivexpalygorund.util.AppConsants
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@ViewModelScope
@Singleton
class FlowableExampleUseCase {
    operator fun invoke(): Disposable? {
        //Flowable is a backpressure safe observable
        return Flowable
            .range(0,100000)
            .onBackpressureBuffer()
            .observeOn(Schedulers.computation())
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {task->
                Log.d(AppConsants.TAG,"This is the task: $task")
            },
                {
                    Log.e(AppConsants.TAG,"onError: ${it.message}")
                },
                {
                    Log.d(AppConsants.TAG, "Task completed")
                })

    }
}