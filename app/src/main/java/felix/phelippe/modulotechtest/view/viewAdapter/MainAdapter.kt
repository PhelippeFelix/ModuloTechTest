package felix.phelippe.modulotechtest.view.viewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import felix.phelippe.modulotechtest.R
import felix.phelippe.modulotechtest.models.classes.Device
import felix.phelippe.modulotechtest.view.viewHolder.MainViewHolder
import java.util.Collections.addAll

class MainAdapter (private val devices:MutableList<Device>) : RecyclerView.Adapter<MainViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.activity_main_list_item,p0,false))
    }

    override fun getItemCount(): Int {
        return if (devices.isNotEmpty()) devices.size else 0
    }

    override fun onBindViewHolder(p0: MainViewHolder, p1: Int) {
        p0.updateWithData(this.devices[p1], p0.adapterPosition)
    }

    fun addDevices(devices: List<Device>) {
        this.devices.apply {
            clear()
            addAll(devices)
        }
    }
}