package felix.phelippe.modulotechtest.models.classes

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Heater (
    @PrimaryKey val id: Int,
    val temperature: Int,
    val mode: Boolean,
    val deviceName: String,
    val productType: String) {

    fun toDevice():Device{
        val modeToString:String = if(mode) "ON" else "OFF"
        return Device(deviceName,id,0,modeToString,0,productType,temperature)
    }
}