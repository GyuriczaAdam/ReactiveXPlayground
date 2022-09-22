package hu.gyuriczaadam.reactivexpalygorund.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId") @Expose
    private var userId: Int = 0,

    @SerializedName("id")
@Expose
private val id:Int = 0,

@SerializedName("title")
@Expose
 val title: String? = null,

@SerializedName("body")
@Expose
 val body: String? = null,

private val comments: List<Comment>? = null
)
