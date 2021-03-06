package felix.phelippe.modulotechtest.models.classes

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Light (
    @PrimaryKey
    val id: Int,
    val intensity: Int,
    val mode: Boolean,
    val deviceName: String,
    val productType: String){
    fun toDevice():Device{
        val modeToString:String = if(mode) "ON" else "OFF"
        return Device(deviceName,id,intensity,modeToString,0,productType,0)
    }
}