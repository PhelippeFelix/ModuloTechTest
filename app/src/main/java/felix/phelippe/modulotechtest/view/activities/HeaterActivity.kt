package felix.phelippe.modulotechtest.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import felix.phelippe.modulotechtest.R
import felix.phelippe.modulotechtest.models.di.Injection
import felix.phelippe.modulotechtest.models.classes.Heater
import felix.phelippe.modulotechtest.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_heater.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HeaterActivity : AppCompatActivity() , CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    lateinit var mainViewModel: MainViewModel
    lateinit var heater: Heater
    var mode:Boolean=false
    var temperature:Double=7.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heater)
        val id = intent.extras!!.getInt("idHeater",0)

        setupViewModel()
        setupData(id)
    }

    private fun setupOnClickItems() {
        deleteHeaterButton.setOnClickListener {
            launch {
                mainViewModel.deleteHeater(heater)
                finish()
            }
        }
            validateHeaterButton.setOnClickListener {
                launch {
                    mainViewModel.updateHeater(
                        Heater(
                            heater.id,
                            temperature.toInt(),
                            mode,
                            heater.deviceName,
                            "Heater"
                        )
                    )
                    finish()
                }
            }
            heaterMode.setOnClickListener {
                mode = if (mode) {
                    heaterMode.text = resources.getText(R.string.OFF)
                    heaterMode.setTextColor((ContextCompat.getColor(this,R.color.red)))
                    heaterMode.isChecked = false
                    false
                } else {
                    heaterMode.text = resources.getText(R.string.ON)
                    heaterMode.setTextColor((ContextCompat.getColor(this,R.color.green_500)))
                    heaterMode.isChecked = true
                    true
                }
            }
            moreButton.setOnClickListener {
                if (temperature < 28 && temperature >= 7) {
                    temperature += 0.5
                    print(temperature)
                    retrieveTemperature()
                }
            }
            lessButton.setOnClickListener {
                if (temperature <= 28 && temperature > 7) {
                    temperature -= 0.5
                    print(temperature)
                    retrieveTemperature()
                }
            }
        }



    private fun setupData(id:Int) {
        launch {
            heater = mainViewModel.getHeaterById(id)
            setupOnClickItems()
            setupUi(heater)
        }
    }

    private fun retrieveTemperature(){
        temperatureView.text = "$temperature°"
    }

    private fun setupUi(heater: Heater) {
        mode = heater.mode
        temperature = heater.temperature.toDouble()
        if (mode){
            heaterMode.text = resources.getText(R.string.ON)
            heaterMode.setTextColor(ContextCompat.getColor(this,R.color.green_500))
            heaterMode.isChecked = true }
        else {
            heaterMode.text = resources.getText(R.string.OFF)
            heaterMode.setTextColor(ContextCompat.getColor(this,R.color.red))
            heaterMode.isChecked = false }
        retrieveTemperature()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory(this)
        ).get(MainViewModel::class.java)
    }
}