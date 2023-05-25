package com.example.lab71

import FromAPI
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.lab71.retrofit.GetFacts
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factDescription = findViewById<TextView>(R.id.tvFactDescription)
        val buttonGetFact = findViewById<TextView>(R.id.buttonGet)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.boredapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val fromAPI = retrofit.create(FromAPI::class.java)

        buttonGetFact.setOnClickListener {
            fromAPI.getFact().enqueue(object : retrofit2.Callback<GetFacts> {
                override fun onResponse(call: Call<GetFacts>, response: Response<GetFacts>) {
                    if (response.isSuccessful) {
                        val description = response.body()
                        factDescription.text = description?.activity ?: "Факт не знайдено"
                    } else {
                        factDescription.text = "Не вдалося отримати факт"
                    }
                }
                override fun onFailure(call: Call<GetFacts>, t: Throwable) {
                    factDescription.text = "Помилка: ${t.message}"
                }
            })
        }
    }
}