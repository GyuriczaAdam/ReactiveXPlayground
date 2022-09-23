package hu.gyuriczaadam.reactivexpalygorund.di

import hu.gyuriczaadam.reactivexpalygorund.data.flatmap_example.RequestApi
import hu.gyuriczaadam.reactivexpalygorund.data.flatmap_example.dto.Post
import hu.gyuriczaadam.reactivexpalygorund.data.operators_example.Task
import hu.gyuriczaadam.reactivexpalygorund.data.repository.FromFutureExampleRepositoryImpl
import hu.gyuriczaadam.reactivexpalygorund.domain.repositories.FromFutureExampleRepository
import hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.*
import hu.gyuriczaadam.reactivexpalygorund.util.AppConsants
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import toothpick.InjectConstructor

@InjectConstructor
class AppModule (
    private val reactiveXUseCases: ReactiveXUseCases
        ) {
    fun provideGetPostsUseCase(): Observable<List<Post?>?>? {
        return reactiveXUseCases.getPostsObservableUseCase(provideRetrofitApi())
    }
    private fun provideTaskList():List<Task>{
        return reactiveXUseCases.getTaskListUseCase()
    }
    fun provideCreateObservableFromListOfObjectsUseCase(): Disposable? {
        return reactiveXUseCases.createObservableFromListOperatorExampleUseCase(provideTaskList())
    }
    fun provideFlowableExample(): Disposable? {
        return reactiveXUseCases.flowableExampleUseCase()
    }
    fun provideJustOperatorTestUseCase(): Disposable? {
        return reactiveXUseCases.justOperatorTestUseCase()
    }
    fun provideRangeOperatorTestUseCase(): Disposable? {
        return reactiveXUseCases.rangeOperatorExampleUseCase()
    }
    private fun provideTaskObject():Task{
        return reactiveXUseCases.provideTaskObjectUseCase()
    }
    fun provideCreateObservableFromTask(): Disposable? {
        return reactiveXUseCases.createOperatorExampleUseCase(provideTaskObject())
    }
    fun provideIntervalExample():Observable<Long>{
        return reactiveXUseCases.intervalOperatorExampleUseCase()
    }
    fun provideFromFutureRepostiory():FromFutureExampleRepository{
        return FromFutureExampleRepositoryImpl(provideRetrofitApi())
    }
    private fun provideRetrofitApi(): RequestApi {
        return Retrofit.Builder()
            .baseUrl(AppConsants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RequestApi::class.java)
    }
}