package felix.phelippe.modulotechtest.models.database.repository

import felix.phelippe.modulotechtest.models.classes.MainUser
import felix.phelippe.modulotechtest.models.database.TestDatabase

class MainUserDataRepository (private val database: TestDatabase) {

    // --- GET ---

    suspend fun getMainUser() : MainUser {
        return database.mainUserDao().getMainUser() }

    // --- CREATE ---

    suspend fun insertMainUser(mainUser: MainUser): Long{
        return database.mainUserDao().insertMainUser(mainUser)
    }

    // --- UPDATE ---

    suspend fun updateMainUser(mainUser: MainUser) :Int {
        return database.mainUserDao().updateMainUser(mainUser)
    }
}