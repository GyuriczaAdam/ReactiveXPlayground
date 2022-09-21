package hu.gyuriczaadam.reactivexpalygorund.data

import hu.gyuriczaadam.reactivexpalygorund.data.dto.Comment
import hu.gyuriczaadam.reactivexpalygorund.data.dto.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestApi {
    @GET("posts")
    fun getPosts(): Observable<List<Post?>?>?

    @GET("posts/{id}/comments")
    fun getComments(
        @Path("id") id: Int
    ): Observable<List<Comment?>?>?
}