package felix.phelippe.modulotechtest.models.classes

import java.io.Serializable

data class Device (
    val deviceName: String,
    val id: Int,
    val intensity: Int,
    val mode: String,
    val position: Int,
    val productType: String,
    val temperature: Int
){
    fun toHeater():Heater{
        return Heater(id,temperature,mode=="ON",deviceName,productType)
    }

    fun toLight():Light{
        return Light(id,intensity,mode=="ON",deviceName,productType)
    }

    fun toRollerShutter():RollerShutter{
        return RollerShutter(id,position,deviceName,productType)
    }
}