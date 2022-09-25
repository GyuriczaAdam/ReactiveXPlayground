package hu.gyuriczaadam.reactivexpalygorund.domain.repositories

import androidx.lifecycle.LiveData
import io.reactivex.Observable
import okhttp3.ResponseBody
import java.util.concurrent.Future

interface FromFutureExampleRepository {
    fun makeFutureQuery(): Future<Observable<ResponseBody?>?>
    fun makeReactiveQuery(): LiveData<ResponseBody?>
}