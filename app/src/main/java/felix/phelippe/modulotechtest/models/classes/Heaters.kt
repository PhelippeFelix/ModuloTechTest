package felix.phelippe.modulotechtest.models.classes

class Heaters (
    val temperature: Int,
    val mode: Boolean,
    deviceName: String,
    id: Int,
    productType: String) : Devices(id, deviceName, productType)