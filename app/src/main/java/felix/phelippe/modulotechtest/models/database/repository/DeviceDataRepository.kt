package felix.phelippe.modulotechtest.models.database.repository

import felix.phelippe.modulotechtest.models.classes.Heater
import felix.phelippe.modulotechtest.models.classes.Light
import felix.phelippe.modulotechtest.models.classes.RollerShutter
import felix.phelippe.modulotechtest.models.database.TestDatabase


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

    // --- GET BY ID ---

    suspend fun getLightById(index:Int) : Light {
        return database.deviceDao().getLightById(index)
    }

    suspend fun getHeaterById(index:Int) : Heater {
        return database.deviceDao().getHeaterById(index)
    }

    suspend fun getRollerShutterById(index:Int) : RollerShutter {
        return database.deviceDao().getRollerShutterById(index)
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

    // --- DELETE ---

    suspend fun deleteLight(light: Light) {
        return database.deviceDao().deleteLight(light)
    }

    suspend fun deleteHeater(heater: Heater) {
        return database.deviceDao().deleteHeater(heater)
    }

    suspend fun deleteRollerShutter(rollerShutter: RollerShutter) {
        return database.deviceDao().deleteRollerShutter(rollerShutter)
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