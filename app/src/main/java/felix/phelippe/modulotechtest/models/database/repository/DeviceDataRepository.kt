package felix.phelippe.modulotechtest.models.database.repository

import androidx.lifecycle.LiveData
import androidx.sqlite.db.SimpleSQLiteQuery
import felix.phelippe.modulotechtest.models.classes.Heater
import felix.phelippe.modulotechtest.models.classes.Light
import felix.phelippe.modulotechtest.models.classes.MainUser
import felix.phelippe.modulotechtest.models.classes.RollerShutter
import felix.phelippe.modulotechtest.models.database.TestDatabase
import felix.phelippe.modulotechtest.models.database.dao.DeviceDao
import io.reactivex.Observable

class DeviceDataRepository(private val database:TestDatabase) {
    // --- GET ---

    suspend fun getLights() : List<Light> {
        return database.deviceDao().getLights()
    }

    suspend fun getHeaters() : List<Heater> {
        return database.deviceDao().getHeaters()
    }

    suspend fun getRollerShutters() : List<RollerShutter> {
        return database.deviceDao().getRollerShutters()
    }

    // --- CREATE ---

    suspend fun insertLight(light: Light): Long {
        return database.deviceDao().insertLight(light)
    }

    suspend fun insertHeater(heater: Heater): Long {
        return database.deviceDao().insertHeater(heater)
    }

    suspend fun insertRollerShutter(rollerShutter: RollerShutter): Long {
        return database.deviceDao().insertRollerShutter(rollerShutter)
    }

    // --- UPDATE ---
    suspend fun updateLight(light: Light) :Int {
        return database.deviceDao().updateLight(light)
    }

    suspend fun updateHeater(heater: Heater) :Int {
        return database.deviceDao().updateHeater(heater)
    }

    suspend fun updateRollerShutter(rollerShutter: RollerShutter) :Int {
        return database.deviceDao().updateRollerShutter(rollerShutter)
    }
}