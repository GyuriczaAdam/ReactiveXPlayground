package hu.gyuriczaadam.reactivexpalygorund.di

import androidx.lifecycle.LiveData
import hu.gyuriczaadam.reactivexpalygorund.data.flatmap_example.RequestApi
import hu.gyuriczaadam.reactivexpalygorund.data.flatmap_example.dto.Post
import hu.gyuriczaadam.reactivexpalygorund.data.operators_example.Task
import hu.gyuriczaadam.reactivexpalygorund.data.repository.FromFutureExampleRepositoryImpl
import hu.gyuriczaadam.reactivexpalygorund.domain.repositories.FromFutureExampleRepository
import hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.*
import hu.gyuriczaadam.reactivexpalygorund.util.AppConsants
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import toothpick.InjectConstructor

@InjectConstructor
class AppModule (
    private val reactiveXUseCases: ReactiveXUseCases,
    private val commonModule: CommonModule
        ) {
    fun provideGetPostsUseCase(): Observable<List<Post?>?>? {
        return reactiveXUseCases.getPostsObservableUseCase(provideRetrofitApi())
    }

    fun provideBufferSimpleExample():Disposable?{
        return reactiveXUseCases.bufferExampleUseCase(commonModule.provideTaskList())
    }

    private fun provideExtractionUseCase(): Function<Task, String> {
        return reactiveXUseCases.extractDescriptionUseCase()
    }

    fun provideMapExampleUseCase():Disposable?{
       return reactiveXUseCases.mapTaskToStringUseCase(provideExtractionUseCase(),commonModule.provideTaskList())
    }

    fun provideCreateObservableFromListOfObjectsUseCase(): Disposable? {
        return reactiveXUseCases.createObservableFromListOperatorExampleUseCase(commonModule.provideTaskList())
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
   /* private fun provideTaskObject():Task{
        return reactiveXUseCases.provideTaskObjectUseCase()
    }*/
    fun provideLiveDataConverterUseCase():LiveData<ResponseBody?>{
        return reactiveXUseCases.convertObservableToLiveDataExampleUseCase(provideFromFutureRepostiory())
    }
    fun provideCreateObservableFromTask(): Disposable? {
        return reactiveXUseCases.createOperatorExampleUseCase(commonModule.provideTaskObject())
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