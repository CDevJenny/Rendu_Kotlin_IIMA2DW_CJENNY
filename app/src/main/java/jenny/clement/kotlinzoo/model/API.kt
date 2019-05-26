package jenny.clement.kotlinzoo.model

import retrofit2.Call
import retrofit2.http.GET

interface API {

    @GET("/posts")
    fun getPosts(): Call<List<Post>>

    @GET("/users")
    fun getUsers(): Call<List<User>>
}