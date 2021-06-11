package felix.phelippe.modulotechtest.models.Di

import android.content.Context
import felix.phelippe.modulotechtest.models.database.TestDatabase
import felix.phelippe.modulotechtest.models.database.repository.DeviceDataRepository
import felix.phelippe.modulotechtest.models.database.repository.MainUserDataRepository
import felix.phelippe.modulotechtest.viewModel.ViewModelFactory


object Injection {
    fun provideDeviceDataSource(context: Context): DeviceDataRepository {
        val database = TestDatabase.getInstance(context)
        return DeviceDataRepository(database)
    }

    fun provideMainUserDataSource(context: Context): MainUserDataRepository {
        val database = TestDatabase.getInstance(context)
        return MainUserDataRepository(database)
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSourceEstate = provideDeviceDataSource(context)
        val dataSourceImage = provideMainUserDataSource(context)
        return ViewModelFactory(dataSourceEstate, dataSourceImage)
    }
}