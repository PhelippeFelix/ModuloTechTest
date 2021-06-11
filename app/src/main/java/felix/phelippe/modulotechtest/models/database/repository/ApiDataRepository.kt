package felix.phelippe.modulotechtest.models.database.repository

import felix.phelippe.modulotechtest.models.api.ApiHelper

class ApiDataRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}