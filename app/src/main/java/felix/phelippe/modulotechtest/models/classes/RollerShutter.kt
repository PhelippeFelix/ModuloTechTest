package felix.phelippe.modulotechtest.models.classes

class RollerShutter(
    val position: Int,
    val mode : Boolean,
    deviceName: String,
    id: Int,
    productType: String) : Devices(id, deviceName, productType)