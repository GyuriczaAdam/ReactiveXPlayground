package hu.gyuriczaadam.reactivexpalygorund.data.flatmap_example

import hu.gyuriczaadam.reactivexpalygorund.data.flatmap_example.dto.Comment
import hu.gyuriczaadam.reactivexpalygorund.data.flatmap_example.dto.Post
import io.reactivex.Flowable
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path


interface RequestApi {
    @GET("posts")
    fun getPosts(): Observable<List<Post?>?>?

    @GET("posts/{id}/comments")
    fun getComments(
        @Path("id") id: Int
    ): Observable<List<Comment?>?>?

    @GET("todos/1")
    fun makeObservableQuery(): Observable<ResponseBody?>?

    @GET("todos/1")
    fun makeQuery(): Flowable<ResponseBody?>?
}