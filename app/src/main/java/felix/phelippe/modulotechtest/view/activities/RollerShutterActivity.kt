package felix.phelippe.modulotechtest.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import felix.phelippe.modulotechtest.R
import felix.phelippe.modulotechtest.models.di.Injection
import felix.phelippe.modulotechtest.models.classes.RollerShutter
import felix.phelippe.modulotechtest.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_roller_shutter.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RollerShutterActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    lateinit var mainViewModel: MainViewModel
    lateinit var rollerShutter: RollerShutter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roller_shutter)
        val id = intent.extras!!.getInt("idRollerShutter",0)

        setupViewModel()
        setupData(id)
    }

    private fun setupOnClickItems() {
        deleteRollerShutterButton.setOnClickListener {
            launch {
                mainViewModel.deleteRollerShutter(rollerShutter)
                finish()
            }
        }
        validateRollerShutterButton.setOnClickListener {
            launch {
                mainViewModel.updateRollerShutter(RollerShutter(
                    rollerShutter.id,
                    positionBar.progress,
                    rollerShutter.deviceName,
                    "RollerShutter"))
                finish()
            }
        }
    }

    private fun setupData(id:Int) {
        launch {
            rollerShutter= mainViewModel.getRollerShutterById(id)
            setupOnClickItems()
            setupUi(rollerShutter)
        }
    }

    private fun setupUi(rollerShutter: RollerShutter) {
        positionBar.progress = rollerShutter.position
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory(this)
        ).get(MainViewModel::class.java)
    }
}