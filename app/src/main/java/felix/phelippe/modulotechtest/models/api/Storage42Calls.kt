package felix.phelippe.modulotechtest.models.api

import felix.phelippe.modulotechtest.models.classes.Data
import felix.phelippe.modulotechtest.models.classes.Device
import felix.phelippe.modulotechtest.models.classes.User
import felix.phelippe.modulotechtest.models.classes.MainUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.ref.WeakReference

object Storage42Calls {
    fun fetchDevices(callbacks: Callbacks?) {
        val callbacksWeakReference = WeakReference(callbacks)
        val googlePlaceSearch = Storage42.retrofit.create(
            Storage42::class.java
        )
        val call = googlePlaceSearch.getDevices()
        call!!.enqueue(object : Callback<Data?> {
            override fun onResponse(call: Call<Data?>, response: Response<Data?>) {
                if (callbacksWeakReference.get() != null) {
                    val deviceGet = response.body()
                    val devices: List<Device?> = deviceGet!!.devices
                    val importedUser:User = deviceGet.user
                    val user = MainUser(1,importedUser.address.toString(),importedUser.birthDate,importedUser.firstName,importedUser.lastName)
                    callbacksWeakReference.get()!!.onResponse(devices,user)
                }
            }

            override fun onFailure(call: Call<Data?>, t: Throwable) {
                println(t.toString())
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get()!!.onFailure()
            }
        })
    }

    interface Callbacks {
        fun onResponse(resultList: List<Device?>, mainUser: MainUser)
        fun onFailure()
    }
}