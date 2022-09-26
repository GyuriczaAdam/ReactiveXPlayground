package hu.gyuriczaadam.reactivexpalygorund.di

import hu.gyuriczaadam.reactivexpalygorund.data.operators_example.Task
import hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.ReactiveXUseCases
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import toothpick.InjectConstructor

@InjectConstructor
class TransformationOperatorsModule(
    private val reactiveXUseCases: ReactiveXUseCases
) {
    private fun provideExtractionUseCase():Function<Task,String>{
        return reactiveXUseCases.extractDescriptionUseCase()
    }

}