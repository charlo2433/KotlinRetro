package com.verus.kotlinretro

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rv__list_posts.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(
            GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/").build()

        val postsApi = retrofit.create(ApiInterface::class.java)

        val compositeDisposable = CompositeDisposable()
        val response = postsApi.getData()

        val disposable = response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe{
            rv__list_posts.adapter = Adapter(it, this)
        }
        compositeDisposable.add(disposable)
        compositeDisposable.dispose()



//        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe {
//            rv__list_posts.adapter = Adapter(it, this)
//        }()

    }

//    private fun getDetails(){
//       val destinationService = ServiceBuilder.builderService(ApiInterface::class.java)
//        val call = destinationService.getData()
//        call.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe{
//            rv__list_posts.adapter = Adapter(it, this)
//        }
//    }r
}
