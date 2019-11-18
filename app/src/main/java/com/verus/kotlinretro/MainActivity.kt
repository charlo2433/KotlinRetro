package com.verus.kotlinretro

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeNetworkCall()


//        rv__list_posts.layoutManager = LinearLayoutManager(this)
//
//        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(
//            GsonBuilder().create()))
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .baseUrl("https://jsonplaceholder.typicode.com/").build()
//
//        val postsApi = retrofit.create(APIInterface::class.java)
//
//        val compositeDisposable = CompositeDisposable()
//        val response = postsApi.getData()
//
//        val disposable = response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe{
//            rv__list_posts.adapter = Adapter(it, this)
//        }
//
//        compositeDisposable.add(disposable)
//        compositeDisposable.dispose()
//


//        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe {
//            rv__list_posts.adapter = Adapter(it, this)
//        }()

    }

    private fun makeNetworkCall() {

        val apiInterface = APIClient.getClient("").create(APIInterface::class.java)

        val call = apiInterface.getData()
        call.enqueue(object : Callback<List<Post>> {

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val postList = response.body()
                    if (postList != null) {
                        loopResponse(postList)
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Failed to load posts...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Failed to load posts...",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    private fun loopResponse(response: List<Post>) {

        for (post in response) {
            Log.d("Item ", "Title : " + post.title)
        }
    }

//    private fun getDetails(){
//       val destinationService = ServiceBuilder.builderService(APIInterface::class.java)
//        val call = destinationService.getData()
//        call.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe{
//            rv__list_posts.adapter = Adapter(it, this)
//        }
//    }r
}
