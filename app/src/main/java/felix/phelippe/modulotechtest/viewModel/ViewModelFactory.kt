package felix.phelippe.modulotechtest.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import felix.phelippe.modulotechtest.models.api.ApiHelper
import felix.phelippe.modulotechtest.models.database.repository.ApiDataRepository
import felix.phelippe.modulotechtest.models.database.repository.DeviceDataRepository
import felix.phelippe.modulotechtest.models.database.repository.MainUserDataRepository

class ViewModelFactory(private val deviceDataRepository: DeviceDataRepository,
                       private val mainUserDataRepository: MainUserDataRepository,
                       private val apiDataRepository: ApiDataRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(deviceDataRepository,mainUserDataRepository,apiDataRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}