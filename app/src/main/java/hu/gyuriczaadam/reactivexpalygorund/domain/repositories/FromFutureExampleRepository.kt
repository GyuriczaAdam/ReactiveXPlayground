package hu.gyuriczaadam.reactivexpalygorund.domain.repositories

import hu.gyuriczaadam.reactivexpalygorund.data.flatmap_example.RequestApi
import io.reactivex.Observable
import okhttp3.ResponseBody
import java.util.concurrent.Future

interface FromFutureExampleRepository {
    fun makeFutureQuery(): Future<Observable<ResponseBody?>?>
}