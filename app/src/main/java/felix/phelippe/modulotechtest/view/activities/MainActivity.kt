package felix.phelippe.modulotechtest.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import felix.phelippe.modulotechtest.R
import felix.phelippe.modulotechtest.models.Di.Injection
import felix.phelippe.modulotechtest.models.classes.*
import felix.phelippe.modulotechtest.models.utils.DeviceConverter
import felix.phelippe.modulotechtest.models.utils.Status
import felix.phelippe.modulotechtest.models.utils.Status.SUCCESS
import felix.phelippe.modulotechtest.view.viewAdapter.MainAdapter
import felix.phelippe.modulotechtest.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity() : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    lateinit var adapter: MainAdapter
    lateinit var mainViewModel: MainViewModel
    lateinit var converter: DeviceConverter
    lateinit var mainUser: MainUser
    var filters = mutableListOf<String>()
    private var rollerShutters: List<RollerShutter> = mutableListOf()
    private var lights: List<Light> = mutableListOf()
    private var heater: List<Heater> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        converter = DeviceConverter()
    }

    override fun onStart() {
        setViewModel()
        setupOnClickButtons()
        setupData()
        super.onStart()
    }

    private fun setupData() {
        launch {
            if (mainViewModel.getMainUser()==null)
                dataFromApi()
            else {
                dataFromDatabase()
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun dataFromDatabase() {
        launch {
            rollerShutters = mainViewModel.getRollerShutters()
            lights = mainViewModel.getLights()
            heater = mainViewModel.getHeaters()
            mainUser = mainViewModel.getMainUser()
            filter()
        }
    }


    private fun setViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory(this)
        ).get(MainViewModel::class.java)
    }

    private fun setupOnClickButtons() {
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        LightFilter.setOnClickListener {
            if (filters.contains("light")) {
                filters.remove("light")
                LightFilter.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.grey_500
                    )
                )
            } else {
                filters.add("light")
                LightFilter.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.grey_700
                    )
                )
            }
            filter()
        }
        RollerShutterFilter.setOnClickListener {
            if (filters.contains("rollerShutter")) {
                filters.remove("rollerShutter")
                RollerShutterFilter.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.grey_500
                    )
                )
            } else {
                filters.add("rollerShutter")
                RollerShutterFilter.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.grey_700
                    )
                )
            }
            filter()
        }
        HeaterFilter.setOnClickListener {
            if (filters.contains("heater")) {
                filters.remove("heater")
                HeaterFilter.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.grey_500
                    )
                )
            } else {
                filters.add("heater")
                HeaterFilter.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.grey_700
                    )
                )
            }
            filter()
        }
    }

    private fun filter(){
        var listOfDevice: MutableList<Device> = mutableListOf()
        if (filters.contains("light"))
            listOfDevice.addAll(converter.lightToListDevice(lights))
        if (filters.contains("rollerShutter"))
            listOfDevice.addAll(converter.rollerShutterToListDevice(rollerShutters))
        if (filters.contains("heater"))
            listOfDevice.addAll(converter.heaterToListDevice(heater))
        if (filters.isEmpty())
            listOfDevice.addAll(converter.convertAllToDevice(rollerShutters,lights,heater))
        retrieveList(listOfDevice)
    }

    private fun dataFromApi() {
        mainViewModel.getData().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        lottie.visibility = View.GONE
                        resource.data?.let { data ->
                            mainUser = data.user.toMainUser()
                            dataFromApiToDatabase(data) }
                    }
                    Status.ERROR -> {
                        lottie.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        lottie.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun dataFromApiToDatabase(data: Data) {
        launch {
            for (i in converter.selectHeater(data.devices))
                mainViewModel.insertHeater(i)
            for (i in converter.selectLights(data.devices))
                mainViewModel.insertLight(i)
            for (i in converter.selectRollerShutter(data.devices))
                mainViewModel.insertRollerShutter(i)
            mainViewModel.insertMainUser(data.user.toMainUser())
            dataFromDatabase()
        }
    }

    private fun setupUi(devices : List<Device>) {
            MainRecyclerView.layoutManager = LinearLayoutManager(this)
            this.adapter = MainAdapter(devices.toMutableList())
            MainRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    MainRecyclerView.context,
                    (MainRecyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
            MainRecyclerView.adapter = adapter
        }

    private fun retrieveList(devices : List<Device>) {
        setupUi(devices)
        adapter.apply {
            addDevices(devices)
            notifyDataSetChanged()
        }
    }
}


