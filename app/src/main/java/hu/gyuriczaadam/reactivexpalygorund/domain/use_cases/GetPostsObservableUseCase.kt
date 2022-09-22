package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases

import hu.gyuriczaadam.reactivexpalygorund.data.flatmap_example.RequestApi
import io.reactivex.Observable
import hu.gyuriczaadam.reactivexpalygorund.data.flatmap_example.dto.Post
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
@ViewModelScope
class GetPostsObservableUseCase {
    operator fun invoke(requestApi: RequestApi): Observable<List<Post?>?>? {
        return requestApi.getPosts()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())

    }
}