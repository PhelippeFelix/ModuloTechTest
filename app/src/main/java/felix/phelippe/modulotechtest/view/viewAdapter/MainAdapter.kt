package felix.phelippe.modulotechtest.view.viewAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import felix.phelippe.modulotechtest.R
import felix.phelippe.modulotechtest.models.classes.Device
import felix.phelippe.modulotechtest.view.viewHolder.MainViewHolder

class MainAdapter (private val deviceTypeConnected:List<Device>) : RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.activity_main_list_item,p0,false))
    }

    override fun getItemCount(): Int {
        return if (deviceTypeConnected.isNotEmpty()) deviceTypeConnected.size else 0
    }

    fun getDeviceDetails(position: Int): Device? {
        return this.deviceTypeConnected[position]
    }

    override fun onBindViewHolder(p0: MainViewHolder, p1: Int) {
        p0.updateWithData(this.deviceTypeConnected[p1], p0.adapterPosition)
    }
}