package felix.phelippe.modulotechtest.view.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import felix.phelippe.modulotechtest.R
import felix.phelippe.modulotechtest.models.classes.Device
import kotlinx.android.synthetic.main.activity_main_list_item.view.*

class MainViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun updateWithData(deviceType: Device, position:Int){
        val glide: RequestManager = Glide.with(itemView)

        itemView.productName.text = deviceType.deviceName
        itemView.productType.text = deviceType.productType

        when (deviceType.productType) {
            "Light" -> {
                glide.load(R.drawable.light)
                    .apply(RequestOptions()
                        .centerCrop())
                    .into(itemView.list_image)
            }
            "Heater" -> {
                glide.load(R.drawable.heater)
                    .apply(RequestOptions()
                        .centerCrop())
                    .into(itemView.list_image)
            }
            "RollerShutter" -> {
                glide.load(R.drawable.rollershutter)
                    .apply(RequestOptions()
                        .centerCrop())
                    .into(itemView.list_image)
            }
        }
    }
}