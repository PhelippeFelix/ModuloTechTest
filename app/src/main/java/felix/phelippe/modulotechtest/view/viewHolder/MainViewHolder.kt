package felix.phelippe.modulotechtest.view.viewHolder

import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import felix.phelippe.modulotechtest.R
import felix.phelippe.modulotechtest.models.classes.Device
import felix.phelippe.modulotechtest.view.activities.HeaterActivity
import felix.phelippe.modulotechtest.view.activities.LightActivity
import felix.phelippe.modulotechtest.view.activities.RollerShutterActivity
import kotlinx.android.synthetic.main.activity_main_list_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class MainViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    fun updateWithData(device: Device){
        val glide: RequestManager = Glide.with(itemView)

        itemView.productName.text = device.deviceName
        itemView.productType.text = device.productType

        when (device.productType) {
            "Light" -> {
                if (device.mode=="ON")
                    glide.load(R.drawable.lighton).apply(RequestOptions().centerCrop()).into(itemView.list_image)
                else
                    glide.load(R.drawable.lightoff).apply(RequestOptions().centerCrop()).into(itemView.list_image)
            }
            "Heater" -> {
                if (device.mode=="ON")
                    glide.load(R.drawable.heateron).apply(RequestOptions().centerCrop()).into(itemView.list_image)
                else
                    glide.load(R.drawable.heateroff).apply(RequestOptions().centerCrop()).into(itemView.list_image)
            }
            "RollerShutter" -> {
                println(device.position)
                when {
                    device.position>=70 -> glide.load(R.drawable.rollershutteron).apply(RequestOptions().centerCrop()).into(itemView.list_image)
                    device.position<=30 -> glide.load(R.drawable.rollershutteroff).apply(RequestOptions().centerCrop()).into(itemView.list_image)
                    device.position in 31..69 -> glide.load(R.drawable.rollershuttersemion).apply(RequestOptions().centerCrop()).into(itemView.list_image)
                }
            }
        }

        itemView.itemClickable.setOnClickListener {
            when (device.productType) {
                "Light" -> {
                    val context = itemView.context
                    val intent = Intent(context, LightActivity::class.java)
                    intent.putExtra("idLight",device.id)
                    context.startActivity(intent)
                }
                "Heater" -> {
                    val context = itemView.context
                    val intent = Intent(context, HeaterActivity::class.java)
                    intent.putExtra("idHeater",device.id)
                    context.startActivity(intent)
                }
                "RollerShutter" -> {
                    val context = itemView.context
                    val intent = Intent(context, RollerShutterActivity::class.java)
                    intent.putExtra("idRollerShutter",device.id)
                    context.startActivity(intent)
                }
            }
        }
    }
}