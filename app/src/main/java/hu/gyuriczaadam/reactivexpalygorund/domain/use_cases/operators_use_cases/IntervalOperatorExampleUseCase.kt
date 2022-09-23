package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.operators_use_cases

import hu.gyuriczaadam.reactivexpalygorund.data.operators_example.Task
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ViewModelScope
@Singleton
class IntervalOperatorExampleUseCase {
    operator fun invoke(): Observable<Long> {
        //Emit an observable every time interval

        return Observable
            //Emits observables from array
            //.fromArray(array)
            //Emits observable from an iterable(like a list)
            //.fromIterable(list)
            .timer(1,TimeUnit.SECONDS)
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())

            //Emit an observable every time interval
            //.interval(1,TimeUnit.SECONDS)
            //Emit a single observable after a given delay

    }
}