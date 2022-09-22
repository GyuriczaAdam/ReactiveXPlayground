package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases

import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Singleton

@ViewModelScope
@Singleton
class JustOperatorTestUseCase {
    operator fun invoke():Observable<String>{
        //With just operator you can create an observable with the maximum of 10 objects
        return Observable.just("one","two","three","four")
    }
}