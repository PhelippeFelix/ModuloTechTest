package felix.phelippe.modulotechtest.models.Di

import android.content.Context
import felix.phelippe.modulotechtest.models.api.ApiHelper
import felix.phelippe.modulotechtest.models.api.RetrofitBuilder
import felix.phelippe.modulotechtest.models.database.TestDatabase
import felix.phelippe.modulotechtest.models.database.repository.ApiDataRepository
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

    fun provideApiDataSource(context: Context) :ApiDataRepository {
        return ApiDataRepository(ApiHelper(RetrofitBuilder.apiService))
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSourceEstate = provideDeviceDataSource(context)
        val dataSourceImage = provideMainUserDataSource(context)
        val apiDataRepository = provideApiDataSource(context)
        return ViewModelFactory(dataSourceEstate, dataSourceImage, apiDataRepository)
    }
}