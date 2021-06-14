package felix.phelippe.modulotechtest.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import felix.phelippe.modulotechtest.R
import felix.phelippe.modulotechtest.models.Di.Injection
import felix.phelippe.modulotechtest.models.classes.Heater
import felix.phelippe.modulotechtest.models.classes.Light
import felix.phelippe.modulotechtest.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_heater.*
import kotlinx.android.synthetic.main.activity_light.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class LightActivity : AppCompatActivity() , CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    lateinit var mainViewModel: MainViewModel
    lateinit var light: Light
    var mode:Boolean=false


    override fun onCreate(savedInstanceSttate: Bundle?) {
        super.onCreate(savedInstanceSttate)
        setContentView(R.layout.activity_light)
        val id = intent.extras!!.getInt("idLight")

        setupViewModel()
        setupData(id)
    }

    private fun setupOnClickItems() {
        deleteLightButton.setOnClickListener {
            launch {
                mainViewModel.deleteLight(light)
                finish()
            }
        }
        validateLightButton.setOnClickListener {
            launch {
                mainViewModel.updateLight(Light(
                        light.id,
                        luminosityBar.progress,
                        mode,light.deviceName,
                        "Light"))
                finish()
            }
        }
        lightMode.setOnClickListener {
            mode = if (mode) {
                lightMode.text = resources.getText(R.string.OFF)
                lightMode.setTextColor((ContextCompat.getColor(this,R.color.red)))
                lightMode.isChecked = false
                false
            } else {
                lightMode.text = resources.getText(R.string.ON)
                lightMode.setTextColor((ContextCompat.getColor(this,R.color.green_500)))
                lightMode.isChecked = true
                true
            }
        }
    }

    private fun setupData(id:Int) {
        launch {
            light = mainViewModel.getLightById(id)
            setupOnClickItems()
            setupUi(light)
        }
    }

    private fun setupUi(light: Light) {
        mode = light.mode
        if (mode){
            lightMode.text = resources.getText(R.string.ON)
            lightMode.setTextColor(ContextCompat.getColor(this,R.color.green_500))
            lightMode.isChecked = true }
        else {
            lightMode.text = resources.getText(R.string.OFF)
            lightMode.setTextColor(ContextCompat.getColor(this,R.color.red))
            lightMode.isChecked = false }
        luminosityBar.progress = light.intensity
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory(this)
        ).get(MainViewModel::class.java)
    }
}