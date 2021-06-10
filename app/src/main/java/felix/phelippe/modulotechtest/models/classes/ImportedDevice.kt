package felix.phelippe.modulotech_test.models.classes

data class ImportedDevice(
    val deviceName: String,
    val id: Int,
    val intensity: Int,
    val mode: String,
    val position: Int,
    val productType: String,
    val temperature: Int
)