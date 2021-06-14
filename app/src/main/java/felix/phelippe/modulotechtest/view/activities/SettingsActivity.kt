package felix.phelippe.modulotechtest.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import felix.phelippe.modulotechtest.R
import felix.phelippe.modulotechtest.models.Di.Injection
import felix.phelippe.modulotechtest.models.classes.Address
import felix.phelippe.modulotechtest.models.classes.MainUser
import felix.phelippe.modulotechtest.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.coroutines.CoroutineContext

class SettingsActivity() : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    lateinit var mainViewModel: MainViewModel
    lateinit var mainUser: MainUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setViewModel()
        setupData()
        setupOnClickItems()
    }

    private fun setupOnClickItems() {
        validateButton.setOnClickListener {
            if (isEmailValid(emailEditText.text.toString())||emailEditText.text.isEmpty()){
                    launch {
                        val newMainUserInfo = MainUser(
                            1,
                            cityEditText.text.toString(),
                            countryEditText.text.toString(),
                            postalCodeEditText.text.toString().toInt(),
                            streetEditText.text.toString(),
                            streetCodeEditText.text.toString(),
                            mainUser.birthDate,
                            firstNameEditText.text.toString(),
                            lastNameEditText.text.toString(),
                            emailEditText.text.toString())
                        mainViewModel.updateUser(newMainUserInfo)
                        finish()
                    }
            } else {
                Toast.makeText(this, "Veuillez mettre des informations valide", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupData() {
        launch {
            mainUser = mainViewModel.getMainUser()
            setInfo()
        }
    }

    private fun setInfo() {
        cityEditText.setText(mainUser.addressCity)
        countryEditText.setText(mainUser.addressCountry)
        postalCodeEditText.setText(mainUser.addressPostalCode.toString())
        streetEditText.setText(mainUser.addressStreet)
        streetCodeEditText.setText(mainUser.addressStreetCode)
        emailEditText.setText(mainUser.email)
        firstNameEditText.setText(mainUser.firstName)
        lastNameEditText.setText(mainUser.lastName)
    }

    private fun setViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory(this)
        ).get(MainViewModel::class.java)
    }

    fun isEmailValid(email: String?): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }
}