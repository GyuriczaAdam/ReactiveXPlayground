package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases

import hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.operators_use_cases.*
import hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.return_object_use_cases.GetTaskListUseCase
import hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.return_object_use_cases.ProvideTaskObjectUseCase
import toothpick.InjectConstructor

@InjectConstructor
data class ReactiveXUseCases(
    val createObservableFromListOperatorExampleUseCase: CreateObservableFromListOperatorExampleUseCase,
    val createOperatorExampleUseCase: CreateOperatorExampleUseCase,
    val justOperatorTestUseCase: JustOperatorTestUseCase,
    val rangeOperatorExampleUseCase: RangeOperatorExampleUseCase,
    val getTaskListUseCase: GetTaskListUseCase,
    val provideTaskObjectUseCase: ProvideTaskObjectUseCase,
    val flowableExampleUseCase: FlowableExampleUseCase,
    val getPostsObservableUseCase: GetPostsObservableUseCase,
    val intervalOperatorExampleUseCase: IntervalOperatorExampleUseCase,
    val convertObservableToLiveDataExampleUseCase: ConvertObservableToLiveDataExampleUseCase
)
