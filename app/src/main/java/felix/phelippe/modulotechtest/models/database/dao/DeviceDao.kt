package felix.phelippe.modulotechtest.models.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import felix.phelippe.modulotechtest.models.classes.Heater
import felix.phelippe.modulotechtest.models.classes.Light
import felix.phelippe.modulotechtest.models.classes.RollerShutter

@Dao
interface DeviceDao {

    @Insert
    suspend fun insertLight(light: Light): Long

    @Update
    suspend fun updateLight(light: Light) :Int

    @Delete
    suspend fun deleteLight (light: Light)

    @Query ("SELECT * FROM Light")
    suspend fun getLights() : List<Light>

    @Query("SELECT * FROM Light WHERE Light.id = :index")
    suspend fun getLightById(index:Int) : Light

    @Insert
    suspend fun insertHeater(heater: Heater): Long

    @Update
    suspend fun updateHeater(heater: Heater) :Int

    @Delete
    suspend fun deleteHeater (heater: Heater)

    @Query ("SELECT * FROM Heater")
    suspend fun getHeaters() : List<Heater>

    @Query("SELECT * FROM Heater WHERE Heater.id = :index")
    suspend fun getHeaterById(index:Int) : Heater

    @Insert
    suspend  fun insertRollerShutter(rollerShutter: RollerShutter): Long

    @Update
    suspend  fun updateRollerShutter(rollerShutter: RollerShutter) :Int

    @Delete
    suspend   fun deleteRollerShutter (rollerShutter: RollerShutter)

    @Query ("SELECT * FROM RollerShutter")
    suspend fun getRollerShutters() : List<RollerShutter>

    @Query("SELECT * FROM RollerShutter WHERE RollerShutter.id = :index")
    suspend fun getRollerShutterById(index:Int) : RollerShutter
}