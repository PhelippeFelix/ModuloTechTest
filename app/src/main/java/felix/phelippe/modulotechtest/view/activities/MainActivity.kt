package felix.phelippe.modulotechtest.view.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import felix.phelippe.modulotechtest.R
import felix.phelippe.modulotechtest.models.Di.Injection
import felix.phelippe.modulotechtest.models.api.Storage42Calls
import felix.phelippe.modulotechtest.models.classes.*
import felix.phelippe.modulotechtest.view.viewAdapter.MainAdapter
import felix.phelippe.modulotechtest.viewModel.MainViewModel
import felix.phelippe.modulotechtest.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity() : AppCompatActivity(),  Storage42Calls.Callbacks, CoroutineScope{
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    lateinit var adapter:MainAdapter
    lateinit var listOfDevice:List<Device>
    var filters = mutableListOf<String>()
    private lateinit var mainViewModel:MainViewModel
    lateinit var mainUser:MainUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this,Injection.provideViewModelFactory(this)).get(MainViewModel::class.java)

        configureOnClickButtons()
    }

    private suspend fun loadData() {
        if(mainViewModel.getMainUser() == null){
            Storage42Calls.fetchDevices(this)
            lottie.visibility = View.VISIBLE
        } else {
            print("aaaaaaaaa")
            println(mainViewModel.getMainUser())}
    }

    private fun configureOnClickButtons() {
        OkFilter.setOnClickListener {
            launch {
                loadData()
            }
            lottie.visibility = View.VISIBLE
        }
        LightFilter.setOnClickListener {
            if(filters.contains("light")){
                filters.remove("light")
                LightFilter.setBackgroundColor(ContextCompat.getColor(this,R.color.design_default_color_primary))
                println(filters)
            }else {
                filters.add("light")
                LightFilter.setBackgroundColor(ContextCompat.getColor(this,R.color.design_default_color_primary_dark))
            }
        }
        RollerShutterFilter.setOnClickListener {
            if(filters.contains("rollerShutter")){
                filters.remove("rollerShutter")
                RollerShutterFilter.setBackgroundColor(ContextCompat.getColor(this,R.color.design_default_color_primary))
            }else {
                filters.add("rollerShutter")
                RollerShutterFilter.setBackgroundColor(ContextCompat.getColor(this,R.color.design_default_color_primary_dark))
            }
        }
        HeaterFilter.setOnClickListener {
            if(filters.contains("Heater")){
                filters.remove("Heater")
                HeaterFilter.setBackgroundColor(ContextCompat.getColor(this,R.color.design_default_color_primary))
            } else {
                filters.add("Heater")
                HeaterFilter.setBackgroundColor(ContextCompat.getColor(this,R.color.design_default_color_primary_dark))
            }
        }
    }

    private fun configureRecyclerView(){
        this.adapter = MainAdapter(listOfDevice)
        MainRecyclerView.adapter = adapter
        MainRecyclerView.layoutManager = LinearLayoutManager(this)
        MainRecyclerView.addItemDecoration(
            DividerItemDecoration(this, 0)
        )
    }

    override fun onResponse(resultList: List<Device?>, mainUser: MainUser) {
        listOfDevice = resultList as List<Device>
        this.mainUser = mainUser
        configureRecyclerView()
        lottie.visibility = View.GONE
    }

    override fun onFailure() {
        println("Erreur dans le retour du callback")
    }
}