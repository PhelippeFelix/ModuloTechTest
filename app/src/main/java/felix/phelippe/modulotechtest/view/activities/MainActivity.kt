package felix.phelippe.modulotechtest.view.activities

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
    lateinit var mainUser: MainUser
    var listOfDevice: MutableList<Device> = mutableListOf()
    var filters = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setViewModel()
        setupOnClickButtons()
        setupUi()
        setupObservers()
    }

    private fun setViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory(this)
        ).get(MainViewModel::class.java)
    }

    private suspend fun loadData() {
        if (mainViewModel.getMainUser() == null) {
            listOfDevice
        } else {
            print("aaaaaaaaa")
            println(mainViewModel.getMainUser())
        }
    }

    private fun setupOnClickButtons() {
        OkFilter.setOnClickListener {
            launch {
                loadData()
            }
            lottie.visibility = View.VISIBLE
        }
        LightFilter.setOnClickListener {
            if (filters.contains("light")) {
                filters.remove("light")
                LightFilter.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.design_default_color_primary
                    )
                )
                println(filters)
            } else {
                filters.add("light")
                LightFilter.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.design_default_color_primary_dark
                    )
                )
            }
        }
        RollerShutterFilter.setOnClickListener {
            if (filters.contains("rollerShutter")) {
                filters.remove("rollerShutter")
                RollerShutterFilter.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.design_default_color_primary
                    )
                )
            } else {
                filters.add("rollerShutter")
                RollerShutterFilter.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.design_default_color_primary_dark
                    )
                )
            }
        }
        HeaterFilter.setOnClickListener {
            if (filters.contains("Heater")) {
                filters.remove("Heater")
                HeaterFilter.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.design_default_color_primary
                    )
                )
            } else {
                filters.add("Heater")
                HeaterFilter.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.design_default_color_primary_dark
                    )
                )
            }
        }
    }

    private fun setupObservers() {
        mainViewModel.getData().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        lottie.visibility = View.GONE
                        resource.data?.let { data -> retrieveList(data) }
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

        private fun setupUi() {
            MainRecyclerView.layoutManager = LinearLayoutManager(this)
            this.adapter = MainAdapter(listOfDevice)
            MainRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    MainRecyclerView.context,
                    (MainRecyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
            MainRecyclerView.adapter = adapter
        }

    private fun retrieveList(data: Data) {

        adapter.apply {
            listOfDevice = data.devices as MutableList<Device>
            mainUser = MainUser(1,data.user.address.toString(),data.user.birthDate,data.user.firstName,data.user.lastName)
            addDevices(listOfDevice)
            notifyDataSetChanged()
        }
    }
}


