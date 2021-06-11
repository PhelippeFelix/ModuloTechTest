package felix.phelippe.modulotechtest.viewModel

import androidx.lifecycle.ViewModel
import felix.phelippe.modulotechtest.models.classes.Heater
import felix.phelippe.modulotechtest.models.classes.Light
import felix.phelippe.modulotechtest.models.classes.MainUser
import felix.phelippe.modulotechtest.models.classes.RollerShutter
import felix.phelippe.modulotechtest.models.database.repository.ApiDataRepository
import felix.phelippe.modulotechtest.models.database.repository.DeviceDataRepository
import felix.phelippe.modulotechtest.models.database.repository.MainUserDataRepository
import felix.phelippe.modulotechtest.models.utils.Resource
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.liveData


class MainViewModel (
    private val deviceDataRepository: DeviceDataRepository,
    private val mainUserDataRepository: MainUserDataRepository,
    private val apiDataRepository: ApiDataRepository) : ViewModel() {


    // --------------------
    // API
    // --------------------

    fun getData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiDataRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


    // --------------------
    // Device
    // --------------------

    // --- GET ---

    suspend  fun getLights() : List<Light> {
        return deviceDataRepository.getLights()
    }

    suspend fun getHeaters() : List<Heater> {
        return deviceDataRepository.getHeaters()
    }

    suspend  fun getRollerShutters() : List<RollerShutter> {
        return deviceDataRepository.getRollerShutters()
    }

    // --- CREATE ---

    suspend fun insertLight(light: Light): Long {
        return deviceDataRepository.insertLight(light)
    }

    suspend  fun insertHeater(heater: Heater): Long {
        return deviceDataRepository.insertHeater(heater)
    }

    suspend  fun insertRollerShutter(rollerShutter: RollerShutter): Long {
        return deviceDataRepository.insertRollerShutter(rollerShutter)
    }

    // --- UPDATE ---
    suspend fun updateLight(light: Light) :Int {
        return deviceDataRepository.updateLight(light)
    }

    suspend fun updateHeater(heater: Heater) :Int {
        return deviceDataRepository.updateHeater(heater)
    }

    suspend fun updateRollerShutter(rollerShutter: RollerShutter) :Int {
        return deviceDataRepository.updateRollerShutter(rollerShutter)
    }


    // --------------------
    // MAIN USER
    // --------------------

    // --- GET ---

    suspend fun getMainUser() : MainUser {
        return mainUserDataRepository.getMainUser() }

    // --- CREATE ---

    suspend fun insertMainUser(mainUser: MainUser): Long{
        return mainUserDataRepository.insertMainUser(mainUser)
    }

    // --- UPDATE ---

    suspend fun updateUser(mainUser: MainUser) :Int {
        return mainUserDataRepository.updateMainUser(mainUser)

    }
}