package com.gmail.part1.satrio.ini_tugas_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.SnapHelper
import android.util.Log
import android.view.View
import android.widget.Toast
import com.gmail.part1.satrio.ini_tugas_kotlin.Adapter.Berita_Adapter
import com.gmail.part1.satrio.ini_tugas_kotlin.Pojo.Berita_Pojo
import com.gmail.part1.satrio.ini_tugas_kotlin.Service.InitRetrofit
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rvNews)

        fetchApi()
    }
    fun fetchApi(){
        val api =  InitRetrofit().getInitInstance()
        val call = api.requestNews("id","sports","9c7105d8f508476f862e6127ae7bd0fa")
        call.enqueue(object : Callback<Berita_Pojo> {
            override fun onFailure(call: Call<Berita_Pojo>?, t: Throwable?) {
                container.visibility = View.GONE
                tvNotif.visibility = View.VISIBLE
                Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Berita_Pojo>?, response: retrofit2.Response<Berita_Pojo>?) {
                if (response != null) {
                    if (response.isSuccessful) {
                        val data = response.body()?.articles
                        val adapter = Berita_Adapter(this@MainActivity, data)
                        rvNews.adapter = adapter
                        val layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                        rvNews.layoutManager = layoutManager
                        pageIndicator.attachTo(rvNews)
                    }
                    //Cara Lihat -> Logcat -> Verbose -> kolom pencarian ini tag dibawah//
                    Log.d("Lihat1","Respon JSON " + response)
                    Log.d("Lihat2","Hasil JSON " + response.body().toString())
                }
            }

        })
    }
}
