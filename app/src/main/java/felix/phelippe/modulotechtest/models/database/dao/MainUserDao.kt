package felix.phelippe.modulotechtest.models.database.dao

import androidx.room.*
import felix.phelippe.modulotechtest.models.classes.MainUser

@Dao
interface MainUserDao {
    @Insert
    suspend fun insertMainUser(mainUser: MainUser) : Long

    @Update
    suspend fun updateMainUser(mainUser: MainUser) :Int

    @Query("SELECT * FROM MainUser WHERE id=1")
    suspend fun getMainUser() : MainUser
}