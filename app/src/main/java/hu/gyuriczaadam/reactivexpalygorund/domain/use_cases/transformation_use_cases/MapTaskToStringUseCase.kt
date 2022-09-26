package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.transformation_use_cases

import android.util.Log
import hu.gyuriczaadam.reactivexpalygorund.data.operators_example.Task
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import hu.gyuriczaadam.reactivexpalygorund.util.AppConsants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Singleton

@ViewModelScope
@Singleton
class MapTaskToStringUseCase {
    operator fun invoke(function: Function<Task,String>,taskList:List<Task>):Disposable?{
        return Observable
            .fromIterable(taskList)
            .map(function)
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d(AppConsants.TAG,"onNext: $it")
                },
                {
                    Log.e(AppConsants.TAG,"onError: ${it.message}")
                },
                {
                    Log.d(AppConsants.TAG, "Map task completed")
                }
            )
    }
}