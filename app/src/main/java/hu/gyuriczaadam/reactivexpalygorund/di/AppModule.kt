package hu.gyuriczaadam.reactivexpalygorund.di


import hu.gyuriczaadam.reactivexpalygorund.data.flatmap_example.RequestApi
import hu.gyuriczaadam.reactivexpalygorund.data.flatmap_example.dto.Post
import hu.gyuriczaadam.reactivexpalygorund.data.operators_example.Task
import hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.GetObservableTaskUseCase
import hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.GetPostsObservableUseCase
import hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.ProvideTaskObjectUseCase
import hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.ReturnRandomNumUseCase
import hu.gyuriczaadam.reactivexpalygorund.util.AppConsants
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import toothpick.InjectConstructor
import javax.inject.Singleton

@Singleton
@ViewModelScope
@InjectConstructor
class AppModule (
    private val returnRandomNumUseCase: ReturnRandomNumUseCase,
    private val getPostsObservableUseCase: GetPostsObservableUseCase,
    private val provideTaskObjectUseCase: ProvideTaskObjectUseCase,
    private val getObservableTaskUseCase: GetObservableTaskUseCase
        ) {
    fun providerRandIntUseCase():Int{
        return returnRandomNumUseCase()
    }
    fun provideGetPostsUseCase(): Observable<List<Post?>?>? {
        return getPostsObservableUseCase(provideRetrofitApi())
    }
    fun provideTaskObject():Task{
        return provideTaskObjectUseCase()
    }
    fun provideCreateObservableFromTask():Observable<Task>{
        return getObservableTaskUseCase(provideTaskObject())
    }

    fun provideRetrofitApi(): RequestApi {
        return Retrofit.Builder()
            .baseUrl(AppConsants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RequestApi::class.java)
    }
}