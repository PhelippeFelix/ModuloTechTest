package felix.phelippe.modulotechtest.models.classes

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class MainUser(
    @PrimaryKey(autoGenerate = true) var id: Long=1,
    val address: String,
    val birthDate: Long,
    val firstName: String,
    val lastName: String
)