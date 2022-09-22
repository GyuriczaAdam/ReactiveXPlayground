package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases

import android.util.Log
import hu.gyuriczaadam.reactivexpalygorund.data.RequestApi
import io.reactivex.Observable
import hu.gyuriczaadam.reactivexpalygorund.data.dto.Post
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io

import javax.inject.Singleton

@Singleton
@ViewModelScope
class GetPostsObservableUseCase {
    operator fun invoke(requestApi: RequestApi): Observable<List<Post?>?>? {
        return requestApi.getPosts()

    }
}