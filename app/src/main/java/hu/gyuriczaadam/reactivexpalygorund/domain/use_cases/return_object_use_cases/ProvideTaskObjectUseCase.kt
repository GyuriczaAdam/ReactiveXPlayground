package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.return_object_use_cases

import hu.gyuriczaadam.reactivexpalygorund.data.operators_example.Task
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import javax.inject.Singleton

@Singleton
@ViewModelScope
class ProvideTaskObjectUseCase {
    operator fun invoke():Task{
        return Task("Walk th dog",true,5)
    }
}