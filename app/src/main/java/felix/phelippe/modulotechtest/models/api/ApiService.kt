package felix.phelippe.modulotechtest.models.api

import felix.phelippe.modulotechtest.models.classes.Data
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("/modulotest/data")
    suspend fun getData(): Data

}
