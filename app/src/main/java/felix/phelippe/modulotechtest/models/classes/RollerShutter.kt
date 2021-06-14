package felix.phelippe.modulotechtest.models.classes

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class RollerShutter(
    @PrimaryKey
    val id: Int,
    val position: Int,
    val deviceName: String,
    val productType: String){
    fun toDevice()=Device(deviceName,id,0,"null",position,productType,0)
}