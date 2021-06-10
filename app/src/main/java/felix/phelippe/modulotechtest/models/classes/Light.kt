package felix.phelippe.modulotechtest.models.classes

class Light (
    val intensity: Int,
    val mode: Boolean,
    deviceName: String,
    id: Int,
    productType: String) : Devices(id, deviceName, productType)