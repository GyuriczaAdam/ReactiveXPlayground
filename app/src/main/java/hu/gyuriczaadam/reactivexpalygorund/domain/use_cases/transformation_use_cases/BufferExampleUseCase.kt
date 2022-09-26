package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.transformation_use_cases

import android.util.Log
import hu.gyuriczaadam.reactivexpalygorund.data.operators_example.Task
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import hu.gyuriczaadam.reactivexpalygorund.util.AppConsants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Singleton

@ViewModelScope
@Singleton
class BufferExampleUseCase {
    //Buffer: Periodically gather itmes from an observable into bundles and emit the bundles rather than emitting itmes one at a time
    operator fun invoke(taskList:List<Task>):Disposable?{
        return Observable
            .fromIterable(taskList)
            .subscribeOn(io())
            .buffer(2)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d(AppConsants.TAG,"onNext bundle result: -----")
                    for(task in it){
                        Log.d(AppConsants.TAG,"onNext: ${task.description}")
                    }
                },
                {
                    Log.e(AppConsants.TAG,"onError: ${it.message}")
                },
                {
                    Log.d(AppConsants.TAG, "Buffer task completed")
                }
            )

    }
}