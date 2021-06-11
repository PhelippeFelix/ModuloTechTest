package felix.phelippe.modulotechtest.models.api

import felix.phelippe.modulotechtest.models.classes.Data
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Storage42 {
    @GET("/modulotest/data")
    fun getDevices(): Call<Data>

    companion object {
        const val BASE_URL = "http://storage42.com"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}