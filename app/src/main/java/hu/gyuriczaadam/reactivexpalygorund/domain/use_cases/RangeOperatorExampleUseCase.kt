package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases

import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import io.reactivex.Observable
import javax.inject.Singleton

@ViewModelScope
@Singleton
class RangeOperatorExampleUseCase {
    operator fun invoke():Observable<Int>{
        //The observer will loop trough in the given range and on each iteration a heavy task can be done on a background thread
        return Observable
            .range(0,9)
    }
}