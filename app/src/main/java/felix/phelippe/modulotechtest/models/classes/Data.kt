package felix.phelippe.modulotechtest.models.classes

data class Data(
    val devices: List<Device>,
    val user: User
) {
   // fun getDeviceList(): List<DeviceType> {
//        for (i in devices) {
   //         when {
    //            i.intensity > 1 -> {
    //                val light: DeviceType =
    //                    Light(i.intensity, i.mode == "ON", i.deviceName, i.id, i.productType)
    //                returnedList.add(light)
     //           }
    //            i.temperature < 7 -> {
    //                val heater: Heater =
    //                    Heater(i.temperature, i.mode == "ON", i.deviceName, i.id, i.productType)
    //                returnedList.add(heater)
    //            }
    //            i.position < 1 -> {
   //                 val shutter: RollerShutter =
    //                    RollerShutter(i.position, i.mode == "ON", i.deviceName, i.id, i.productType)
    //                returnedList.add(shutter)
   //             }
   //         }
   //     }
  //      return returnedList
   // }
}