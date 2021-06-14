package felix.phelippe.modulotechtest.models.utils

import felix.phelippe.modulotechtest.models.classes.Device
import felix.phelippe.modulotechtest.models.classes.Heater
import felix.phelippe.modulotechtest.models.classes.Light
import felix.phelippe.modulotechtest.models.classes.RollerShutter

class DeviceConverter {
    fun selectRollerShutter(devices: List<Device>):MutableList<RollerShutter>{
        val toReturn:MutableList<RollerShutter> = mutableListOf()
        for (i in devices){
            if (i.productType=="RollerShutter")
                toReturn.add(i.toRollerShutter())
        }
        return toReturn
    }

    fun selectLights(devices:List<Device>):MutableList<Light>{
        val toReturn:MutableList<Light> = mutableListOf()
        for (i in devices){
            if (i.productType=="Light")
                toReturn.add(i.toLight())
        }
        return toReturn
    }

    fun selectHeater(devices:List<Device>):MutableList<Heater>{
        val toReturn:MutableList<Heater> = mutableListOf()
        for (i in devices){
            if (i.productType=="Heater")
                toReturn.add(i.toHeater())
        }
        return toReturn
    }

    fun convertAllToDevice(rollerShutter: List<RollerShutter>,light: List<Light>,heater: List<Heater>):MutableList<Device>{
        val toReturn:MutableList<Device> = mutableListOf()
        for (i in rollerShutter)
            toReturn.add(i.toDevice())
        for (i in light)
            toReturn.add(i.toDevice())
        for (i in heater)
            toReturn.add(i.toDevice())
        return toReturn
    }

    fun heaterToListDevice(heaters:List<Heater>):MutableList<Device>{
        val toReturn:MutableList<Device> = mutableListOf()
        for (i in heaters)
            toReturn.add(i.toDevice())
        return toReturn
    }

    fun rollerShutterToListDevice(rollerShutter: List<RollerShutter>):MutableList<Device>{
        val toReturn:MutableList<Device> = mutableListOf()
        for (i in rollerShutter)
            toReturn.add(i.toDevice())
        return toReturn
    }

    fun lightToListDevice(light: List<Light>):MutableList<Device>{
        val toReturn:MutableList<Device> = mutableListOf()
        for (i in light)
            toReturn.add(i.toDevice())
        return toReturn
    }

}