package felix.phelippe.modulotechtest.models.classes

data class User(
    val address: Address,
    val birthDate: Long,
    val firstName: String,
    val lastName: String
){
    fun toMainUser():MainUser{
        return MainUser(1,
            address.city,
            address.country,
            address.postalCode,
            address.street,
            address.streetCode,
            birthDate,
            firstName,
            lastName,
            null,)
    }
}