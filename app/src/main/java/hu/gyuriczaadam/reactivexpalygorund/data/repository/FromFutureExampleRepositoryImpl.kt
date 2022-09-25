package hu.gyuriczaadam.reactivexpalygorund.data.repository

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.LiveData
import hu.gyuriczaadam.reactivexpalygorund.data.flatmap_example.RequestApi
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import hu.gyuriczaadam.reactivexpalygorund.domain.repositories.FromFutureExampleRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers.io
import okhttp3.ResponseBody
import toothpick.InjectConstructor
import java.util.concurrent.*
import javax.inject.Singleton

@ViewModelScope
@Singleton
@InjectConstructor
class FromFutureExampleRepositoryImpl(
    private val requestApi: RequestApi
):FromFutureExampleRepository {
    override fun makeFutureQuery(): Future<Observable<ResponseBody?>?> {
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        val myNetworkCallable: Callable<Observable<ResponseBody?>?> =
            object : Callable<Observable<ResponseBody?>?> {
                @Throws(Exception::class)
                override fun call(): Observable<ResponseBody?>? {
                    return requestApi.makeObservableQuery()
                }
            }
        val futureObservable: Future<Observable<ResponseBody?>?> =
            object : Future<Observable<ResponseBody?>?> {
                override fun cancel(mayInterruptIfRunning: Boolean): Boolean {
                    if (mayInterruptIfRunning) {
                        executor.shutdown()
                    }
                    return false
                }

                override fun isCancelled(): Boolean {
                    return executor.isShutdown
                }

                override fun isDone(): Boolean {
                    return executor.isTerminated
                }

                @Throws(ExecutionException::class, InterruptedException::class)
                override fun get(): Observable<ResponseBody?>? {
                    return executor.submit(myNetworkCallable).get()
                }

                @Throws(
                    ExecutionException::class,
                    InterruptedException::class,
                    TimeoutException::class
                )
                override fun get(timeout: Long, unit: TimeUnit?): Observable<ResponseBody?>? {
                    return executor.submit(myNetworkCallable).get(timeout,unit)
                }
            }
        return  futureObservable
    }

    override fun makeReactiveQuery(): LiveData<ResponseBody?> {
        return LiveDataReactiveStreams
            .fromPublisher(requestApi
                .makeQuery()
                !!.subscribeOn(io())
            )


    }
}