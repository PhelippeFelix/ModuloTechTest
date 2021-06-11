package felix.phelippe.modulotechtest.models.api

class ApiHelper(private val apiService: ApiService) {
        suspend fun getUsers() = apiService.getData()
    }
