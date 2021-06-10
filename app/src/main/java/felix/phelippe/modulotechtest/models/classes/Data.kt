package felix.phelippe.modulotechtest.models.classes

import felix.phelippe.modulotech_test.models.classes.ImportedDevice

data class Data(
    val importedDevices: List<ImportedDevice>,
    val user: User
)