package jenny.clement.kotlinzoo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import jenny.clement.kotlinzoo.model.API
import jenny.clement.kotlinzoo.model.Post
import jenny.clement.kotlinzoo.model.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cell.*
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adaptater = MyAdaptater()
        recyclerView.adapter = adaptater
        // requetes avec retrofit pour recupérer post et user via une interface (API)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create<API>()

        api.getPosts().enqueue(object : Callback<List<Post>>{
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e("DL", "Download Failed: ${t.message}")
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val posts = response.body()!!
                api.getUsers().enqueue(object : Callback<List<User>>{
                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        Log.e("DL", "Download Failed: ${t.message}")
                    }

                    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                        val users = response.body()!!

                        adaptater.list = posts
                        adaptater.userList = users
                        adaptater.notifyDataSetChanged()
                    }

                })
            }

        })
        // requetes simples de recuperation de posts et users avec okhttp3 & kotson
        /*val postRequest = Request.Builder()
            .get()
            .url("https://jsonplaceholder.typicode.com/posts")
            .build()

        val userRequest = Request.Builder()
            .get()
            .url("https://jsonplaceholder.typicode.com/users")
            .build()
        OkHttpClient().newCall(postRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("DL", "Download failed, unlucky: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.i("DL", "Download Success Grats!")

                val posts = Gson().fromJson<List<Post>>(response.body()!!.string())

                OkHttpClient().newCall(userRequest).enqueue(object : Callback{
                    override fun onFailure(call: Call, e: IOException) {
                        Log.e("DL", "Download Error zbeb")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        Log.i("DL", "Success yikes")

                        val users = Gson().fromJson<List<User>>(response.body()!!.string())

                        runOnUiThread {
                            adaptater.list = posts
                            adaptater.userList = users
                            adaptater.notifyDataSetChanged()
                        }
                    }

                })
            }
        })*/
    }
}
