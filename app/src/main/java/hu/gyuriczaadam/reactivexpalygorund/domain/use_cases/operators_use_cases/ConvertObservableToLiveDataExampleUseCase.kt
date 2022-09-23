package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.operators_use_cases


import android.arch.lifecycle.LiveData
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import hu.gyuriczaadam.reactivexpalygorund.domain.repositories.FromFutureExampleRepository
import okhttp3.ResponseBody
import javax.inject.Singleton

@ViewModelScope
@Singleton
class ConvertObservableToLiveDataExampleUseCase {
    operator fun invoke(repository: FromFutureExampleRepository): LiveData<ResponseBody?> {
        return repository.makeReactiveQuery()
    }
}