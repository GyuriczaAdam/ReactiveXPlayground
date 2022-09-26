package hu.gyuriczaadam.reactivexpalygorund.di

import hu.gyuriczaadam.reactivexpalygorund.data.operators_example.Task
import hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.ReactiveXUseCases
import toothpick.InjectConstructor

@InjectConstructor
class CommonModule(
    private val reactiveXUseCases: ReactiveXUseCases
) {

    fun provideTaskList():List<Task>{
        return reactiveXUseCases.getTaskListUseCase()
    }

     fun provideTaskObject():Task{
        return reactiveXUseCases.provideTaskObjectUseCase()
    }
}