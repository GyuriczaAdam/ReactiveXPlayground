package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.transformation_use_cases

import io.reactivex.functions.Function
import hu.gyuriczaadam.reactivexpalygorund.data.operators_example.Task
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import javax.inject.Singleton

@ViewModelScope
@Singleton
class ExtractDescriptionUseCase {
    operator fun invoke():Function<Task,String>{
        return Function<Task,String>(){
            apply {
                return@Function it.description
            }.toString()
        }
    }
}